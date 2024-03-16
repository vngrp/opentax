package eu.opentax.domain

import eu.opentax.system.nl.Euro

fun interface Calculable<T> {
    fun calculate(context: TaxYear): Euro
}

sealed interface IncomeTax<T : TaxYear> : Calculable<T>

fun interface IncomeTax2024 : IncomeTax<TaxYear2024>
fun interface IncomeTax2023 : IncomeTax<TaxYear2023>

fun calculate(incomeTax: IncomeTax<*>, context: TaxYear): Euro = incomeTax.calculate(context)