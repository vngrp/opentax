package eu.opentax.system.nl

import eu.opentax.domain.IncomeTax
import eu.opentax.domain.TaxYear
import kotlin.math.floor
import kotlin.math.max

class Inkomstenbelasting(
    private val brutoloon: BrutoInkomen
) : IncomeTax {
    context(TaxYear)
    override fun calculate() = when(year) {
        2021 -> berekenBox1In2021(brutoloon)
        2022 -> berekenBox1In2022(brutoloon)
        2023 -> berekenBox1In2023(brutoloon)
        2024 -> berekenBox1In2024(brutoloon)
        else -> throw IllegalArgumentException("Year $year is not supported")
    }

    private fun berekenBox1In2021(bruto: Euro): Euro {
        return 0.0
    }

    private fun berekenBox1In2022(bruto: Euro): Euro {
        return 0.0
    }

    private fun berekenBox1In2023(bruto: Euro): Euro {
        val schijf1 = bruto.coerceIn(0.0, 73_032.00) * 0.3693
        val schijf2 = max(0.0, bruto - 73_032.00) * 0.495

        return floor(schijf1 + schijf2)
    }

    private fun berekenBox1In2024(bruto: Euro): Euro {
        val schijf1 = bruto.coerceIn(0.0, 75_518.00) * 0.3697
        val schijf2 = max(0.0, bruto - 75_518.00) * 0.495

        return floor(schijf1 + schijf2)
    }
}
