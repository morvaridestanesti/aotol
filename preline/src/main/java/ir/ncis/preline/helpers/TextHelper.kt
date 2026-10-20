package ir.ncis.preline.helpers

internal fun Char.toLatinDigit(): Char = when (this) {
    in '۰'..'۹' -> '0' + (this - '۰')
    in '٠'..'٩' -> '0' + (this - '٠')
    else        -> this
}
