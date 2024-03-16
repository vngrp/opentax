package eu.opentax.domain

import eu.opentax.system.nl.Euro

interface Calculable {
    context(TaxYear)
    fun calculate(): Euro
}

interface IncomeTax : Calculable