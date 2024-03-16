package eu.opentax.system.nl.belastingen

import eu.opentax.domain.TaxableIncome
import eu.opentax.system.nl.BrutoInkomen

class BelastbaarInkomen(
    private val bruto: BrutoInkomen,
) : TaxableIncome {
    private val cache by lazy { bruto }

    override fun invoke() = cache
}