package eu.opentax.system.nl.aftrekposten

import eu.opentax.domain.TaxYear2023
import eu.opentax.domain.TaxYear2024
import eu.opentax.system.nl.belastingen.BelastbaarInkomen
import kotlin.math.ceil

class Arbeidskorting(
    val belastbaarInkomen: BelastbaarInkomen
)

context(TaxYear2024)
operator fun Arbeidskorting.invoke(): Double {
    val arbeidskorting = when(val belastbaar = belastbaarInkomen()) {
        in 0.0..11_491.00 -> 0.08425 * belastbaar
        in 11_491.00..24_821.00 -> 968.00 + 0.31433 * (belastbaar - 11_490.00)
        in 24_821.00..39_958.00 -> 5_158.00 + 0.02471 * (belastbaar - 24_820.00)
        in 39_958.00..124_935.00 -> 5_532.00 - 0.06510 * (belastbaar - 39_957.00)
        else -> 0.00
    }

    return ceil(arbeidskorting)
}

context(TaxYear2023)
operator fun Arbeidskorting.invoke(): Double {
    val arbeidskorting = when(val belastbaar = belastbaarInkomen()) {
        in 0.0..10_741.00 -> 0.08231 * belastbaar
        in 10_741.00..23_201.00 -> 884.00 + 0.029861 * (belastbaar - 10_740.00)
        in 23_201.00..37_691.00 -> 4_605.00 + 0.03085 * (belastbaar - 23_201.00)
        in 37_691.00..115_295.00 -> 5_052.00 - 0.06510 * (belastbaar - 37_691.00)
        else -> 0.00
    }

    return ceil(arbeidskorting)
}