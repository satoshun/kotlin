interface S

interface A : S

interface B : S

fun <R> materialize(): R = throw Exception()

fun <K> select(x: K, y: K): K = throw Exception()

//fun foo(vararg values: S) {}
//fun test(a: A, b: B) = foo(a, b)

//fun test(a: A, b: B) = if (true) a else b
//fun test(a: A, b: B) = select(a, b)

//
////fun foo(x: A<*>) = when (x) {
////    is B -> x.value
////    is C -> x.value
////    else -> 1
////}
//
////fun test_1() = if (true) { 1 } else { 1.0 }
//
////fun test_2(x: B, y: C) = select(x, y)
//
//fun test_3(a: A, b: B) = select(a, b)
//
//fun test_4(a: A, b: B) = if (true) a else  b

fun takeA(x: A) {}

//fun test_2() {
//    takeA(if (true) materialize() else materialize())
//}

//fun test_3() {
//    takeA(select(materialize(), materialize()))
//}

//fun test_4() {
//    takeA(select(if (true) materialize() else materialize(), materialize()))
//}

fun test_5() {
    takeA(
        try {
            materialize()
        } catch (e: Exception) {
            materialize()
        } finally {
            materialize()
        }
    )
}

//fun <T> run(block: () -> T): T = block()

//fun test() {
//    takeA(
//        run {
//            materialize()
//        }
//    )
//}