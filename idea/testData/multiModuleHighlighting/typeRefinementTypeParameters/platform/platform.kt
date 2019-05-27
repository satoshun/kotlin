@file:Suppress("UNSUPPORTED_FEATURE")

package foo

interface B

actual interface A : B {
    actual fun commonFun()

    fun platformFun()
}

class AImpl : A {
    override fun commonFun() {}
    override fun platformFun() {}
}

@Suppress("UNUSED_PARAMETER")
fun takeList(inv: List<B>) {}