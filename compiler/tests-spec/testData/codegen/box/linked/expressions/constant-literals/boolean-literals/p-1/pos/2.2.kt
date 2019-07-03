/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-draft
 * PLACE: expressions, constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the labelReference.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

fun `true`() {
    val l = `false`@ {
        return@`false`
    }
    l()
    return@`true`
}

fun box(): String? {
    `true`()

    var i = 0
    `false`@ while (i < 10) {
        i++
        if (i <= 7) {
            continue@`false`
        }
        if (i > 5) {
            break@`false`
        }
    }

    return "OK"
}
