/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: expressions, constant-literals, real-literals -> paragraph 3 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Simple real literals with omitted a fraction part, suffixed by f/F (float suffix).
 */

// TESTCASE NUMBER: 1
val value_1 = 0F

// TESTCASE NUMBER: 2
val value_2 = 00F

// TESTCASE NUMBER: 3
val value_3 = 000f

// TESTCASE NUMBER: 4
val value_4 = 0000F

// TESTCASE NUMBER: 5
val value_5 = 00000000000000000000000000000000000000f

// TESTCASE NUMBER: 6
val value_6 = 1f

// TESTCASE NUMBER: 7
val value_7 = 22f

// TESTCASE NUMBER: 8
val value_8 = 333F

// TESTCASE NUMBER: 9
val value_9 = 4444f

// TESTCASE NUMBER: 10
val value_10 = 55555F

// TESTCASE NUMBER: 11
val value_11 = 666666f

// TESTCASE NUMBER: 12
val value_12 = 7777777f

// TESTCASE NUMBER: 13
val value_13 = 88888888F

// TESTCASE NUMBER: 14
val value_14 = 999999999F

// TESTCASE NUMBER: 15
val value_15 = 123456789f

// TESTCASE NUMBER: 16
val value_16 = 2345678F

// TESTCASE NUMBER: 17
val value_17 = 34567F

// TESTCASE NUMBER: 18
val value_18 = 456F

// TESTCASE NUMBER: 19
val value_19 = 5f

// TESTCASE NUMBER: 20
val value_20 = 654F

// TESTCASE NUMBER: 21
val value_21 = 76543F

// TESTCASE NUMBER: 22
val value_22 = 8765432f

// TESTCASE NUMBER: 23
val value_23 = 987654321F

// TESTCASE NUMBER: 24
val value_24 = <!FLOAT_LITERAL_CONFORMS_INFINITY!>888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111F<!>

// TESTCASE NUMBER: 25
val value_25 = <!FLOAT_LITERAL_CONFORMS_ZERO!>0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001f<!>
