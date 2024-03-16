package eu.opentax.domain

interface TaxableIncome {
    operator fun invoke(): Double
}