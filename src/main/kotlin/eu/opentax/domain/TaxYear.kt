package eu.opentax.domain

sealed class TaxYear(val year: Int)

data object TaxYear2021 : TaxYear(2021)
data object TaxYear2022 : TaxYear(2022)
data object TaxYear2023 : TaxYear(2023)
data object TaxYear2024 : TaxYear(2024)