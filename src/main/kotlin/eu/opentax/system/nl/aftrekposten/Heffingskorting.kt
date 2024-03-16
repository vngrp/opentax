package eu.opentax.system.nl.aftrekposten

import eu.opentax.domain.TaxYear2023
import eu.opentax.domain.TaxYear2024
import eu.opentax.system.nl.Euro
import eu.opentax.system.nl.belastingen.BelastbaarInkomen
import kotlin.math.ceil

class Heffingskorting(
    val belastbaarInkomen: BelastbaarInkomen
)

context(TaxYear2024)
operator fun Heffingskorting.invoke(): Euro {
    val korting = when(val belastbaar = belastbaarInkomen()) {
        in 0.0..24_813.00 -> 3_362.00
        in 24_813.00..75_518.00 -> 3_362.00 - 0.06630 * (belastbaar - 24_813.00)
        else -> 0.00
    }

    return ceil(korting)
}

context(TaxYear2023)
operator fun Heffingskorting.invoke(): Euro {
    val korting = when(val belastbaar = belastbaarInkomen()) {
        in 0.0..22_661.00 -> 3_070.00
        in 22_661.00..73_031.00 -> 3_070.00 - 0.06095 * (belastbaar - 22_661.00)
        else -> 0.00
    }

    return ceil(korting)
}