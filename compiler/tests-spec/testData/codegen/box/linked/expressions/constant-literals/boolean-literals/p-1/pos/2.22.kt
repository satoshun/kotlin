/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-draft
 * PLACE: expressions, constant-literals, boolean-literals -> paragraph 1 -> sentence 2
 * NUMBER: 22
 * DESCRIPTION: The use of Boolean literals as the identifier (with backtick) in the labelDefinition.
 * NOTE: this test data is generated by FeatureInteractionTestDataGenerator. DO NOT MODIFY CODE MANUALLY!
 */

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.EXPRESSION)
annotation class Foo

fun box(): String? {
    val lambda_1 = `true`@ {
        return@`true` false
    }
    val lambda_2 = @Foo `false`@ { Boolean
        return@`false` false
    }

    val x1 = `true`@ true

    var i = 0

    `false`@ while (true) {
        i++
        if (i < 4) continue@`false`
        i++
        if (i > 15) break@`false`
    }

    var j = 0

    `true`@ for (k in 0..20) {
        j++
        if (k < 4) continue@`true`
        j++
        if (k > 15) break@`true`
    }

    if (lambda_1()) return null
    if (lambda_2()) return null
    if (!x1) return null
    if (i != 17) return null
    if (j != 30) return null

    return "OK"
}
