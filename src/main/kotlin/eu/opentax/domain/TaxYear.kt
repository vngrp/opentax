package eu.opentax.domain

sealed class TaxYear

data object TaxYear2021 : TaxYear()
data object TaxYear2022 : TaxYear()
data object TaxYear2023 : TaxYear()
data object TaxYear2024 : TaxYear()