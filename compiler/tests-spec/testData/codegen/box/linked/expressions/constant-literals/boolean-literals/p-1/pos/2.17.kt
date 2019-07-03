// HELPERS: REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-draft
 * PLACE: expressions, constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 17
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the object.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

open class A {
    val x1 = true
}

final object `true` {
    val x1 = false
}

object `false` : A() {
    val x2 = false
}

fun box(): String? {
    if (`true`.x1) return null
    if (!`false`.x1 || `false`.x2) return null

    if (!checkClassName(`true`::class, "true")) return null
    if (!checkClassName(`false`::class, "false")) return null

    return "OK"
}
