// EXPECTED_REACHABLE_NODES: 1280

// Wrong unsafeCast
// DONT_TARGET_EXACT_BACKEND: JS_IR

package foo

// CHECK_NOT_CALLED_IN_SCOPE: scope=box function=isType
// CHECK_NOT_CALLED_IN_SCOPE: scope=box function=throwCCE

fun box(): String {
    // dynamic unsafeCast
    val result = js("\"OK\"").unsafeCast<String>()
    // Any unsafeCast
    val intOk = result.unsafeCast<Int>()

    return result
}