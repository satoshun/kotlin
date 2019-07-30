// HELPERS: REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 26
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the callableReference.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

val Boolean.`true`: Boolean
    get() {
        return true
    }

val Boolean?.`false`: Boolean
    get() {
        return false
    }

fun Int?.`true`(x: Boolean): Boolean {
    return !x
}

fun box(): String? {
    if (!false.`true` || false.`false` || !0.`true`(false)) return null

    if (!checkCallableName(Boolean::`true`, "true") || !Boolean::`true`.get(true)) return null
    if (!checkCallableName(Boolean????::`false`, "false") || Boolean????::`false`.get(false)) return null
    if (!checkCallableName(Boolean????::`false`::equals.call(false)::`true`, "true")
        || !Boolean????::`false`::equals.call(false)::`true`.get()) return null
    if (!checkCallableName(Int?::`true`, "true") || Int?::`true`.call(10, true)) return null

    return "OK"
}
