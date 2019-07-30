/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, real-literals -> paragraph 3 -> sentence 1
 * NUMBER: 4
 * DESCRIPTION: Real literals suffixed by f/F (float suffix) with omitted a whole-number part and an exponent mark.
 */

val value_1 = .0e0f
val value_2 = .0e-00F
val value_3 = .0E000F
val value_4 = .0E+0000f

val value_5 = .0e+0f
val value_6 = .00e00f
val value_7 = .000E-000F

val value_8 = .0E+1F
val value_9 = .00e22F
val value_10 = .345678e00000000001F
val value_11 = .56e-0f
val value_12 = .65e000000000000F
val value_13 = .7654E+010f
val value_14 = .876543E1f
val value_15 = .98765432e-2f
val value_16 = .0987654321E-3f

fun box(): String? {
    val value_17 = .1111e4f
    val value_18 = .22222E-5F
    val value_19 = .33333e+6F
    val value_20 = .444444E7F
    val value_21 = .5555555e8f
    val value_22 = .777777777E-308f
    val value_23 = .99999999999e-309F

    val value_24 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f
    val value_25 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e-000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000F
    val value_26 = .000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e+000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f

    if (value_1.compareTo(.0e0f) != 0 || value_1.compareTo(0.0F) != 0) return null
    if (value_2.compareTo(.0e-00F) != 0 || value_2.compareTo(0.0f) != 0) return null
    if (value_3.compareTo(.0E000F) != 0 || value_3.compareTo(0.0f) != 0) return null
    if (value_4.compareTo(.0E+0000F) != 0 || value_4.compareTo(0.0F) != 0) return null
    if (value_5.compareTo(.0e+0F) != 0 || value_5.compareTo(0.0F) != 0) return null
    if (value_6.compareTo(.00e00f) != 0 || value_6.compareTo(0.0F) != 0) return null
    if (value_7.compareTo(.000E-000F) != 0 || value_7.compareTo(0.0f) != 0) return null

    if (value_8.compareTo(.0E+1f) != 0 || value_8.compareTo(0.0F) != 0) return null
    if (value_9.compareTo(.00e22F) != 0 || value_9.compareTo(0.0F) != 0) return null
    if (value_10.compareTo(.345678e00000000001F) != 0 || value_10.compareTo(3.45678f) != 0) return null
    if (value_11.compareTo(.56e-0f) != 0 || value_11.compareTo(0.56F) != 0) return null
    if (value_12.compareTo(.65e000000000000f) != 0 || value_12.compareTo(0.65f) != 0) return null
    if (value_13.compareTo(.7654E+010F) != 0 || value_13.compareTo(7.654E9f) != 0) return null
    if (value_14.compareTo(.876543E1F) != 0 || value_14.compareTo(8.76543F) != 0) return null
    if (value_15.compareTo(.98765432e-2f) != 0 || value_15.compareTo(0.0098765432F) != 0) return null
    if (value_16.compareTo(.0987654321E-3f) != 0 || value_16.compareTo(9.87654321E-5F) != 0) return null

    if (value_17.compareTo(.1111e4f) != 0 || value_17.compareTo(1111.0F) != 0) return null
    if (value_18.compareTo(.22222E-5F) != 0 || value_18.compareTo(2.2222E-6F) != 0) return null
    if (value_19.compareTo(.33333e+6f) != 0 || value_19.compareTo(333330.0F) != 0) return null
    if (value_20.compareTo(.444444E7F) != 0 || value_20.compareTo(4444440.0F) != 0) return null
    if (value_21.compareTo(.5555555e8f) != 0 || value_21.compareTo(5.555555E7F) != 0) return null
    if (value_22.compareTo(.777777777E-308F) != 0 || value_22.compareTo(7.77777777E-309F) != 0) return null
    if (value_23.compareTo(.99999999999e-309f) != 0 || value_23.compareTo(9.9999999999E-310f) != 0) return null
    if (value_24.compareTo(.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000F) != 0 || value_24.compareTo(0.0F) != 0) return null
    if (value_25.compareTo(.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e-000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f) != 0 || value_25.compareTo(0.0F) != 0) return null
    if (value_26.compareTo(.000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000e+000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f) != 0 || value_26.compareTo(0.0F) != 0) return null

    return "OK"
}
