/*
 * KOTLIN PSI SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, real-literals -> paragraph 4 -> sentence 2
 * NUMBER: 3
 * DESCRIPTION: Real literals with underscores around an exponent mark.
 */

val value = .0__e_0
val value = 2.1_e_2
val value = 1_E-_2F
val value = 3_e+_0f
val value = 5_E_-0
val value = 5_e_-0F
val value = 5_e_00000000

val value = 0.0_0___E____-0__0_0F
val value = 0__0.0_____e___0f
val value = .0_0_E__0_0
val value = 00_______________00.0_0__e+__0_0

val value = 33__3.0_e_10__0
val value = .0_E_0______00F
val value = 5_________555_________5.0____e____-____9
val value = 666_666.0__________________________________________________1_E+_2___________________________________________________________________0F
val value = 9_______9______9_____9____9___9__9_9.0___E__-1

val value = 0_0_0_0_0_0_0_0_0_0.12345678___e+__90F
val value = 1_2_3_4_5_6_7_8_9.2_3_4_5_6_7_8_9_e_-_0
val value = .45_6_E_7f
val value = 456.5__e_0_6
val value = 6_54.76_5_e-_4
val value = 7_6543.8____E____7654_3
val value = 9_____________87654321.0__e__-9_8765432_____________1F

val value = 000000000000000000000000000000000000000000000000000000000000000000000000000000000000000___0.000000000000000000000000_e_000000000000000000000000000000000000000000000000000000000000000_0F
val value = 0_000000000000000000000000000000000000000000000000000000000000000000000000000000000000000.0_E-_0___000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
val value = 9999999999999999999999999999999999999999999_______________999999999999999999999999999999999999999999999.33333333333333333333333333333333333333333333333_333333333333333333333333333333333333333_e_3_3f
