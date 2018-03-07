/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.j2k.tree.impl

import org.jetbrains.kotlin.j2k.tree.*
import org.jetbrains.kotlin.j2k.tree.visitors.JKTransformer
import org.jetbrains.kotlin.j2k.tree.visitors.JKVisitor

class JKJavaFieldImpl(override var modifierList: JKModifierList,
                      override var type: JKType,
                      override var name: JKNameIdentifier,
                      override var initializer: JKExpression?) : JKJavaField, JKElementBase() {
    override val valid: Boolean
        get() = true

    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaField(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) =
        listOfNotNull(type, name, initializer).forEach { it.accept(visitor, data) }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        type = type.transform(transformer, data)
        name = name.transform(transformer, data)
        initializer = initializer?.transform(transformer, data)
    }
}

class JKJavaStringLiteralExpressionImpl(override val text: String) : JKJavaStringLiteralExpression, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaStringLiteralExpression(this, data)
}


class JKJavaAccessModifierImpl(override val type: JKJavaAccessModifier.AccessModifierType) : JKJavaAccessModifier, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaAccessModifier(this, data)
}

class JKJavaModifierImpl(override val type: JKJavaModifier.JavaModifierType) : JKJavaModifier, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaModifier(this, data)
}

class JKJavaMethodImpl(
    override var modifierList: JKModifierList,
    override var name: JKNameIdentifier,
    override var valueArguments: List<JKValueArgument>,
    override var block: JKBlock?
) : JKJavaMethod, JKElementBase() {
    override val valid: Boolean
        get() = true

    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaMethod(this, data)
    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        modifierList = modifierList.transform(transformer, data)
        name = name.transform(transformer, data)
        valueArguments = valueArguments.map { it.transform<JKValueArgument, D>(transformer, data) }
        block = block?.transform(transformer, data)
    }
}

sealed class JKJavaOperatorIdentifierImpl : JKJavaOperatorIdentifier, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaOperatorIdentifier(this, data)

    object PLUS : JKJavaOperatorIdentifierImpl()
    object MINUS : JKJavaOperatorIdentifierImpl()
}

sealed class JKJavaQualificationIdentifierImpl : JKJavaQualificationIdentifier, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaQualificationIdentifier(this, data)

    object DOT : JKJavaQualificationIdentifierImpl()
}

class JKJavaMethodCallExpressionImpl(
    override var identifier: JKMethodReference,
    override var arguments: JKExpressionList
) : JKJavaMethodCallExpression, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaMethodCallExpression(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {
        identifier.accept(visitor, data)
        arguments.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        identifier = identifier.transform(transformer, data)
        arguments = arguments.transform(transformer, data)
    }
}

class JKJavaFieldAccessExpressionImpl(override var identifier: JKReference) : JKJavaFieldAccessExpression, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaFieldAccessExpression(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {
        identifier.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        identifier = identifier.transform(transformer, data)
    }
}

class JKJavaNewExpressionImpl(
    override var identifier: JKClassReference,
    override var arguments: JKExpressionList
) : JKJavaNewExpression, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaNewExpression(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {
        identifier.accept(visitor, data)
    }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        identifier = identifier.transform(transformer, data)
        arguments = arguments.transform(transformer, data)
    }
}

class JKJavaNewEmptyArrayImpl(override var initializer: List<JKLiteralExpression?>) : JKJavaNewEmptyArray, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaNewEmptyArray(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {

    }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        initializer = initializer.map { it?.transform<JKLiteralExpression, D>(transformer, data) }
    }
}

class JKJavaNewArrayImpl(override var initializer: List<JKExpression>) : JKJavaNewArray, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaNewArray(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {

    }

    override fun <D> transformChildren(transformer: JKTransformer<D>, data: D) {
        initializer = initializer.map { it.transform<JKLiteralExpression, D>(transformer, data) }
    }
}

sealed class JKJavaPrimitiveTypeImpl(override val name: String) : JKJavaPrimitiveType, JKElementBase() {

    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaPrimitiveType(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {

    }

    object BYTE : JKJavaPrimitiveTypeImpl("byte")
    object BOOLEAN : JKJavaPrimitiveTypeImpl("boolean")
    object INT : JKJavaPrimitiveTypeImpl("int")
}

class JKJavaArrayTypeImpl(override val type: JKType) : JKJavaArrayType, JKElementBase() {
    override fun <R, D> accept(visitor: JKVisitor<R, D>, data: D): R = visitor.visitJavaArrayType(this, data)

    override fun <D> acceptChildren(visitor: JKVisitor<Unit, D>, data: D) {

    }
}