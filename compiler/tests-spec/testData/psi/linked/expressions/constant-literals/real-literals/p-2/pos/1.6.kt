/*
 * KOTLIN PSI SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, real-literals -> paragraph 2 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: Real literals suffixed by f/F (float suffix) with an exponent mark without digits after it.
 */

val value = 0.0EF

val value = 00.0ef
val value = 000.00e-f
val value = 0000.000E+F

val value = 1.0eF
val value = 22.00E+f
val value = 333.000ef
val value = 4444.0000Ef
val value = 55555.0e+F
val value = 666666.00E-F
val value = 7777777.000eF
val value = 88888888.0000ef
val value = 999999999.0E-F

val value = 7.888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888e+f
val value = 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000eF
val value = 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e-f
val value = 0.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e+F
