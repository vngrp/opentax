package eu.opentax.domain

sealed interface TaxableIncome {
    operator fun invoke(): Double
}

interface TaxableIncome2021 : TaxableIncome
interface TaxableIncome2022 : TaxableIncome
interface TaxableIncome2023 : TaxableIncome
interface TaxableIncome2024 : TaxableIncome
