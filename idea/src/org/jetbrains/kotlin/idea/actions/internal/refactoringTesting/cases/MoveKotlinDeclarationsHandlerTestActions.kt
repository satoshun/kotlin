/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.actions.internal.refactoringTesting.cases

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiElement
import com.intellij.refactoring.move.MoveCallback
import org.jetbrains.kotlin.idea.refactoring.fqName.getKotlinFqName
import org.jetbrains.kotlin.idea.refactoring.move.moveDeclarations.MoveKotlinDeclarationsHandlerActions
import org.jetbrains.kotlin.idea.refactoring.move.moveDeclarations.ui.MoveKotlinNestedClassesModel
import org.jetbrains.kotlin.idea.refactoring.move.moveDeclarations.ui.MoveKotlinNestedClassesToUpperLevelModel
import org.jetbrains.kotlin.idea.refactoring.move.moveDeclarations.ui.MoveKotlinTopLevelDeclarationsModel
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.psi.psiUtil.containingClassOrObject

internal data class TestDataKeeper(var caseData: String)

internal class FailedToRunCaseException : Exception()

internal class MoveKotlinDeclarationsHandlerTestActions(private val caseDataKeeper: TestDataKeeper) : MoveKotlinDeclarationsHandlerActions {

    override fun showErrorHint(project: Project, editor: Editor?, message: String, title: String, helpId: String?) {
        throw FailedToRunCaseException()
    }

    private fun MoveKotlinNestedClassesModel.testDataString(): String {
        return "MoveKotlinNestedClassesModel:" +
                "\nopenInEditorCheckBox = $openInEditorCheckBox\n" +
                "selectedElementsToMove + ${selectedElementsToMove.joinToString { it.getKotlinFqName().toString() }}\n" +
                "targetClass = ${targetClass?.getKotlinFqName() ?: "null"}"
    }

    private fun MoveKotlinNestedClassesToUpperLevelModel.testDataString(): String {
        return "MoveKotlinNestedClassesToUpperLevelModel:\n" +
                "innerClass = ${innerClass.name}\n" +
                "target = ${target.getKotlinFqName()}\n" +
                "parameter = ${parameter ?: "null"}\n" +
                "className = $className\n" +
                "passOuterClass = $passOuterClass\n" +
                "searchInComments = $searchInComments\n" +
                "isSearchInNonJavaFiles = $isSearchInNonJavaFiles\n" +
                "packageName = $packageName\n"
    }

    private fun MoveKotlinTopLevelDeclarationsModel.testDataString(): String {
        return "MoveKotlinTopLevelDeclarationsModel:\n" +
                "elementsToMove = ${elementsToMove.joinToString { it.getKotlinFqName().toString() }}\n" +
                "targetPackage = $targetPackage\n" +
                "selectedPsiDirectory = ${selectedPsiDirectory ?: "null"}\n" +
                "fileNameInPackage = $fileNameInPackage\n" +
                "targetFilePath = $targetFilePath\n" +
                "isMoveToPackage = $isMoveToPackage\n" +
                "isSearchReferences = $isSearchReferences\n" +
                "isSearchInComments = $isSearchInComments\n" +
                "isSearchInNonJavaFiles = $isSearchInNonJavaFiles\n" +
                "isDeleteEmptyFiles = $isDeleteEmptyFiles\n" +
                "isUpdatePackageDirective = $isUpdatePackageDirective\n" +
                "isFullFileMove = $isFullFileMove"
    }

    private fun doWithMoveKotlinNestedClassesModel(nestedClass: KtClassOrObject, targetContainer: PsiElement?) {

        val containingClassOrObject = nestedClass.containingClassOrObject!!

        val elementsToMove = containingClassOrObject.declarations.mapNotNull { declaration ->
            if (declaration !is KtClassOrObject) return@mapNotNull null
            if (declaration is KtClass && declaration.isInner()) return@mapNotNull null
            if (declaration is KtObjectDeclaration && declaration.isCompanion()) return@mapNotNull null
            declaration as KtClassOrObject
        }

        val selectedElements = mutableSetOf<KtClassOrObject>()
        repeat(elementsToMove.size) {
            selectedElements.add(elementsToMove.random())
        }

        val model = MoveKotlinNestedClassesModel(
            nestedClass.project,
            false,
            selectedElements.toList(),
            containingClassOrObject,
            targetContainer,
            null
        )

        caseDataKeeper.caseData = model.testDataString()

        model.computeModelResult(throwOnConflicts = true).run()
    }

    private fun doWithMoveKotlinNestedClassesToUpperLevelModel(nestedClass: KtClassOrObject, targetContainer: PsiElement?) {

        val outerClass = nestedClass.containingClassOrObject ?: throw FailedToRunCaseException()
        val newTarget = targetContainer
            ?: outerClass.containingClassOrObject
            ?: outerClass.containingFile.let { it.containingDirectory ?: it }

        val model = MoveKotlinNestedClassesToUpperLevelModel(
            nestedClass.project,
            nestedClass,
            newTarget,
            randomNullableString(),
            randomClassName(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            randomClassName(),
            isOpenInEditor = false
        )

        caseDataKeeper.caseData = model.testDataString()

        model.computeModelResult(throwOnConflicts = true).run()
    }

    override fun invokeMoveKotlinNestedClassesRefactoring(
        project: Project,
        elementsToMove: List<KtClassOrObject>,
        originalClass: KtClassOrObject,
        targetClass: KtClassOrObject,
        moveCallback: MoveCallback?
    ) = throw NotImplementedError()

    override fun invokeMoveKotlinTopLevelDeclarationsRefactoring(
        project: Project,
        elementsToMove: Set<KtNamedDeclaration>,
        targetPackageName: String,
        targetDirectory: PsiDirectory?,
        targetFile: KtFile?,
        moveToPackage: Boolean,
        searchInComments: Boolean,
        searchForTextOccurrences: Boolean,
        deleteEmptySourceFiles: Boolean,
        moveCallback: MoveCallback?
    ) {
        val selectedElementsToMove = mutableSetOf<KtNamedDeclaration>()
        repeat(elementsToMove.size) {
            selectedElementsToMove.add(elementsToMove.random())
        }

        val model = MoveKotlinTopLevelDeclarationsModel(
            project,
            selectedElementsToMove.toList(),
            if (randomBoolean()) targetPackageName else randomClassName(),
            if (randomBoolean()) targetDirectory else null,
            randomString() + ".kt",
            targetFile?.virtualFile?.path ?: randomString(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            randomBoolean(),
            null
        )

        caseDataKeeper.caseData = model.testDataString()

        model.computeModelResult(throwOnConflicts = true).run()
    }

    override fun invokeKotlinSelectNestedClassChooser(nestedClass: KtClassOrObject, targetContainer: PsiElement?) {
        when {
            targetContainer != null && targetContainer !is KtClassOrObject || nestedClass is KtClass && nestedClass.isInner() -> {
                doWithMoveKotlinNestedClassesToUpperLevelModel(nestedClass, targetContainer)
            }
            nestedClass is KtEnumEntry -> return
            else -> {
                if (randomBoolean()) {
                    doWithMoveKotlinNestedClassesToUpperLevelModel(nestedClass, targetContainer)
                } else {
                    doWithMoveKotlinNestedClassesModel(nestedClass, targetContainer)
                }
            }
        }
    }

    override fun invokeKotlinAwareMoveFilesOrDirectoriesRefactoring(
        project: Project,
        initialDirectory: PsiDirectory?,
        elements: Array<out PsiElement>,
        moveCallback: MoveCallback?
    ) = throw NotImplementedError()
}