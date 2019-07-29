/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.expressions.impl

import com.intellij.psi.PsiElement
import org.jetbrains.kotlin.fir.*
import org.jetbrains.kotlin.fir.expressions.FirBlock
import org.jetbrains.kotlin.fir.expressions.FirCatch
import org.jetbrains.kotlin.fir.expressions.FirTryExpression
import org.jetbrains.kotlin.fir.references.FirStubReference
import org.jetbrains.kotlin.fir.visitors.FirTransformer

class FirTryExpressionImpl(
    session: FirSession,
    psi: PsiElement?,
    override var tryBlock: FirBlock,
    override var finallyBlock: FirBlock?,
    override var calleeReference: FirReference = FirStubReference(session)
) : FirTryExpression(session, psi) {
    override val catches = mutableListOf<FirCatch>()

    override fun <D> transformChildren(transformer: FirTransformer<D>, data: D): FirElement {
        transformTryBlock(transformer, data)
        transformCatches(transformer, data)
        transformFinallyBlock(transformer, data)
        return super.transformChildren(transformer, data)
    }

    override fun <D> transformTryBlock(transformer: FirTransformer<D>, data: D): FirElement {
        tryBlock = tryBlock.transformSingle(transformer, data)
        return this
    }

    override fun <D> transformCatches(transformer: FirTransformer<D>, data: D): FirElement {
        catches.transformInplace(transformer, data)
        return this
    }

    override fun <D> transformFinallyBlock(transformer: FirTransformer<D>, data: D): FirElement {
        finallyBlock = finallyBlock?.transformSingle(transformer, data)
        return this
    }
}