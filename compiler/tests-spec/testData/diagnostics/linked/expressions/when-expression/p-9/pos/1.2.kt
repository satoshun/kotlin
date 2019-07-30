// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: expressions, when-expression -> paragraph 9 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: 'When' least upper bound of the types check (when exhaustive via enum).
 * HELPERS: classes, enumClasses, checkType
 */

// TESTCASE NUMBER: 1
fun case_1(value_1: EnumClass): String {
    val whenValue = when (value_1) {
        EnumClass.EAST -> ClassLevel2()
        EnumClass.NORTH -> ClassLevel3()
        EnumClass.SOUTH -> ClassLevel4()
        EnumClass.WEST -> ClassLevel5()
    }

    whenValue checkType { check<ClassLevel2>() }
    checkSubtype<ClassLevel1>(whenValue)

    return ""
}

// TESTCASE NUMBER: 2
fun case_2(value_1: EnumClass?): String {
    val whenValue = when (value_1) {
        EnumClass.EAST -> ClassLevel2()
        EnumClass.NORTH -> ClassLevel3()
        EnumClass.SOUTH -> ClassLevel4()
        EnumClass.WEST -> ClassLevel5()
        null -> ClassLevel6()
    }

    whenValue checkType { check<ClassLevel2>() }
    checkSubtype<ClassLevel1>(whenValue)

    return ""
}

// TESTCASE NUMBER: 3
fun case_3(value_1: EnumClass): String {
    val whenValue = when (value_1) {
        EnumClass.EAST -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        EnumClass.NORTH -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        EnumClass.SOUTH -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        EnumClass.WEST -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
    }

    whenValue checkType { check<Any>() }
    checkSubtype<Any>(whenValue)

    return ""
}

// TESTCASE NUMBER: 4
fun case_4(value_1: EnumClass?): String {
    val whenValue = when (value_1) {
        EnumClass.EAST -> <!IMPLICIT_CAST_TO_ANY!>10<!>
        EnumClass.NORTH -> <!IMPLICIT_CAST_TO_ANY!>""<!>
        EnumClass.SOUTH -> {<!IMPLICIT_CAST_TO_ANY!>{}<!>}
        EnumClass.WEST -> <!IMPLICIT_CAST_TO_ANY!>object<!> {}
        null -> <!IMPLICIT_CAST_TO_ANY!>false<!>
    }

    whenValue checkType { check<Any>() }
    checkSubtype<Any>(whenValue)

    return ""
}
