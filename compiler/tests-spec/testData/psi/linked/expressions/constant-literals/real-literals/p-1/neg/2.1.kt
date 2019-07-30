/*
 * KOTLIN PSI SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 0.1-85
 * PLACE: constant-literals, real-literals -> paragraph 1 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Not allowed hexadecimal real literals.
 */

val value = 0x100.1
val value = 0x100.0
val value = 0X0.0
val value = 0x00.0
val value = 0x00.000
val value = 0x001.000
val value = 0xFA.1234567890
val value = 0XAAAA.00000000001

val value = 0x110000.41AB4135F54A
val value = 0XAFCE.0ABCDEF
val value = 0x0123456789ABCDEF.0123456789ABCDEF

val value = 0.0X5F
val value = 0.0x913530ABCDEF
val value = 1234567.0x1234567895F
val value = 10000000000.0x913530ABCDEF
val value = 9.10x913530ABCDEF
val value = .0XABCDEF
val value = .0x0123456789

val value = 0x0.0x0
val value = 0X5F.0x11
val value = 0x000001.0x000001
val value = 0XABCDEF.0xABCDEF

val value = 0x0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF.0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF
val value = 0X0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF.01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345
val value = 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345.0X1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123
val value = 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345.0xABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789AB
