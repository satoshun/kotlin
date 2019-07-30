/*
 * KOTLIN PSI SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, real-literals -> paragraph 3 -> sentence 1
 * NUMBER: 6
 * DESCRIPTION: Real literals with omitted a whole-number part, suffixed by f/F (float suffix) followed by an exponent mark without digits.
 */

val value = .0EF
val value = .0ef
val value = .00e-f
val value = .000E+F

val value = .0eF
val value = .00E+f
val value = .000ef
val value = .0000Ef
val value = .0e+F
val value = .00E-F
val value = .000eF
val value = .0000ef
val value = .0E-F

val value = .888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888e+f
val value = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000eF
val value = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e-f
val value = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e+F
