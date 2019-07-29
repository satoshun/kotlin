/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.resolve.transformers

import org.jetbrains.kotlin.descriptors.Modality
import org.jetbrains.kotlin.descriptors.Visibilities
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.copy
import org.jetbrains.kotlin.fir.declarations.impl.FirAnonymousFunctionImpl
import org.jetbrains.kotlin.fir.declarations.impl.FirMemberFunctionImpl
import org.jetbrains.kotlin.fir.declarations.impl.FirTypeParameterImpl
import org.jetbrains.kotlin.fir.declarations.impl.FirValueParameterImpl
import org.jetbrains.kotlin.fir.expressions.FirExpression
import org.jetbrains.kotlin.fir.expressions.FirTryExpression
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression
import org.jetbrains.kotlin.fir.references.FirStubReference
import org.jetbrains.kotlin.fir.resolve.calls.*
import org.jetbrains.kotlin.fir.resolve.createFunctionalType
import org.jetbrains.kotlin.fir.symbols.SyntheticCallableId
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol
import org.jetbrains.kotlin.fir.transformSingle
import org.jetbrains.kotlin.fir.types.*
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImpl
import org.jetbrains.kotlin.fir.types.impl.FirResolvedTypeRefImpl
import org.jetbrains.kotlin.fir.types.impl.FirTypeProjectionWithVarianceImpl
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind
import org.jetbrains.kotlin.types.Variance

fun FirBodyResolveTransformer.generateCalleeForWhenExpression(whenExpression: FirWhenExpression): FirWhenExpression? {
    val stubReference = whenExpression.calleeReference
    assert(stubReference is FirStubReference)

    val function = generateWhenCallDeclaration()

//    val arguments = whenExpression.branches.map {
//        generateAnonymousFunction {
//            body = it.result
//        }
//    }

    val arguments = whenExpression.branches.map { it.result }
    val reference = generateCalleeReferenceWithCandidate(function, arguments, "CALL_FOR_WHEN") ?: return null // TODO

    return whenExpression.copy(calleeReference = reference)
}

fun FirBodyResolveTransformer.generateCalleeForTryExpression(tryExpression: FirTryExpression): FirTryExpression? {
    val stubReference = tryExpression.calleeReference
    assert(stubReference is FirStubReference)

    val function = generateTryCallDeclaration(tryExpression)
    val arguments = mutableListOf<FirExpression>()

    with(tryExpression) {
        arguments += tryBlock
        catches.map {
            arguments += it.block
        }
    }

    val reference = generateCalleeReferenceWithCandidate(function, arguments, "CALL_FOR_TRY") ?: return null // TODO

    return tryExpression.copy(calleeReference = reference)
}



//fun FirBodyResolveTransformer.completeWhenNotFully(whenExpression: FirWhenExpression) {
//    val newWhenExpression = whenExpression.copy()
//    newWhenExpression.transformBranches(this, NotFullTransform)
//}

private fun FirBodyResolveTransformer.generateCalleeReferenceWithCandidate(
    function: FirMemberFunctionImpl,
    arguments: List<FirExpression>,
    name: String
): FirNamedReferenceWithCandidate? {
    val callInfo = generateCallInfo(arguments)
    val candidate = generateCandidate(callInfo, function)
    val applicability = resolutionStageRunner.processCandidate(candidate)
    if (applicability <= CandidateApplicability.INAPPLICABLE) {
        return null
    }

    return FirNamedReferenceWithCandidate(session, null, Name.identifier(name), candidate)
}

private fun FirBodyResolveTransformer.generateCandidate(callInfo: CallInfo, function: FirMemberFunctionImpl): Candidate =
    CandidateFactory(inferenceComponents, callInfo).createCandidate(
        symbol = function.symbol,
        dispatchReceiverValue = null,
        implicitExtensionReceiverValue = null,
        explicitReceiverKind = ExplicitReceiverKind.NO_EXPLICIT_RECEIVER
    )


private fun FirBodyResolveTransformer.generateCallInfo(arguments: List<FirExpression>) = CallInfo(
    callKind = CallKind.Function,
    explicitReceiver = null,
    arguments = arguments,
    isSafeCall = false,
    typeArguments = emptyList(),
    session = session,
    containingFile = file,
    container = container
) { it.resultType }


fun FirBodyResolveTransformer.generateTryCallDeclaration(tryExpression: FirTryExpression): FirMemberFunctionImpl {
    val functionSymbol = FirNamedFunctionSymbol(SyntheticCallableId.TRY)
    val typeParameterSymbol = FirTypeParameterSymbol()
    val typeParameter = FirTypeParameterImpl(session, null, typeParameterSymbol, Name.identifier("K"), Variance.INVARIANT, false)

    val returnType = FirResolvedTypeRefImpl(
        session,
        null,
        ConeTypeParameterTypeImpl(typeParameterSymbol.toLookupTag(), false)
    )

    val tryType = run {
        FirResolvedTypeRefImpl(session, null, returnType.coneTypeUnsafe())
    }

    val catchTypes = tryExpression.catches.map {
        FirResolvedTypeRefImpl(session, null, returnType.coneTypeUnsafe())
    }

    val typeArgument = FirTypeProjectionWithVarianceImpl(session, null, Variance.INVARIANT, returnType)

    return generateMemberFunction(session, functionSymbol, SyntheticCallableId.TRY.callableName, typeArgument.typeRef).apply {
        typeParameters += typeParameter

        valueParameters += tryType.toValueParameter(session, "tryBlock")
        catchTypes.forEachIndexed { i, type ->
            valueParameters += type.toValueParameter(session, "catchBlock_$i")
        }
    }
}

fun FirBodyResolveTransformer.generateWhenCallDeclaration(): FirMemberFunctionImpl {
    val functionSymbol = FirNamedFunctionSymbol(SyntheticCallableId.WHEN)
    val typeParameterSymbol = FirTypeParameterSymbol()
    val typeParameter = FirTypeParameterImpl(session, null, typeParameterSymbol, Name.identifier("K"), Variance.INVARIANT, false)


    val returnType = FirResolvedTypeRefImpl(
        session,
        null,
        ConeTypeParameterTypeImpl(typeParameterSymbol.toLookupTag(), false)
    )

    val branchType = FirResolvedTypeRefImpl(
        session,
        null,
        returnType.coneTypeUnsafe<ConeKotlinType>().createArrayOf(session)
    )

    val typeArgument = FirTypeProjectionWithVarianceImpl(session, null, Variance.INVARIANT, returnType)

    return generateMemberFunction(session, functionSymbol, SyntheticCallableId.WHEN.callableName, typeArgument.typeRef).apply {
        typeParameters += typeParameter
        valueParameters += branchType.toValueParameter(session, "branches", true)
    }
}

private inline fun FirBodyResolveTransformer.generateAnonymousFunction(init: FirAnonymousFunctionImpl.() -> Unit): FirAnonymousFunctionImpl {
    return FirAnonymousFunctionImpl(
        session, null, FirImplicitTypeRefImpl(session, null), null
    ).apply(init).let {
        it.transformSingle(this, null)
    }
}

private fun generateMemberFunction(session: FirSession, symbol: FirNamedFunctionSymbol, name: Name, returnType: FirTypeRef) =
    FirMemberFunctionImpl(
        session = session,
        psi = null,
        symbol = symbol,
        name = name,
        visibility = Visibilities.PUBLIC,
        modality = Modality.FINAL,
        isExpect = false,
        isActual = false,
        isOverride = false,
        isOperator = false,
        isInfix = false,
        isInline = false,
        isTailRec = false,
        isExternal = false,
        isSuspend = false,
        receiverTypeRef = null,
        returnTypeRef = returnType
    )

private fun FirResolvedTypeRef.toValueParameter(session: FirSession, name: String, isVararg: Boolean = false) = FirValueParameterImpl(
    session = session,
    psi = null,
    name = Name.identifier(name),
    returnTypeRef = this,
    defaultValue = null,
    isCrossinline = false,
    isNoinline = false,
    isVararg = isVararg
)