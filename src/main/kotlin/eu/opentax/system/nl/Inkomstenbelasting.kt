package eu.opentax.system.nl

import eu.opentax.domain.TaxYear2023
import eu.opentax.domain.TaxYear2024
import kotlin.math.floor
import kotlin.math.max

class Inkomstenbelasting(
    val belastbaarInkomen: BelastbaarInkomen
)

context(TaxYear2023)
fun Inkomstenbelasting.calculate(): Double {
    val schijf1 = belastbaarInkomen().coerceIn(0.0, 73_032.00) * 0.3693
    val schijf2 = max(0.0, belastbaarInkomen() - 73_032.00) * 0.495

    return floor(schijf1 + schijf2)
}

context(TaxYear2024)
fun Inkomstenbelasting.calculate(): Euro {
    val schijf1 = belastbaarInkomen().coerceIn(0.0, 75_518.00) * 0.3697
    val schijf2 = max(0.0, belastbaarInkomen() - 75_518.00) * 0.495

    return floor(schijf1 + schijf2)
}