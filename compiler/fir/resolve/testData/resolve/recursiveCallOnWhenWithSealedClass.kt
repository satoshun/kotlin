// Issue: KT-18583

interface A {
    fun foo()
}

interface B : A {
    fun bar()
}

//fun test(a: A) {
//    a
//}

fun test(x: Any) {
    when (x) {
        is A, is B -> x
        else null
    }
}