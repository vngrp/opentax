package eu.opentax.system.nl

import eu.opentax.domain.TaxableIncome

class BelastbaarInkomen(
    private val bruto: BrutoInkomen
) : TaxableIncome {
    private val cache by lazy { bruto }

    override fun invoke() = cache
}