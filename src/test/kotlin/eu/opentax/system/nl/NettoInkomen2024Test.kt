package eu.opentax.system.nl

import eu.opentax.domain.TaxYear2023
import eu.opentax.domain.TaxYear2024
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class NettoInkomen2024Test {
    private val testCases2023 = listOf(
        BelastbaarInkomenTestCase(200_000.00, 200_000.00, 110_181.00),
        BelastbaarInkomenTestCase(0.00, 0.00, 0.00),
    )

    private val testCases2024 = listOf(
        BelastbaarInkomenTestCase(200_000.00, 200_000.00, 110_463.00),
        BelastbaarInkomenTestCase(0.00, 0.00, 0.00),
    )

    @Test
    fun `Netto inkomen berekenen in 2023`(): Unit = with(TaxYear2023) {
        testCases2023.forEach {
            // Arrange
            val box1 = Inkomstenbelasting(it.brutoloon)

            // Assert
            assertEquals(it.nettoloon, it.brutoloon - box1.calculate())
        }
    }

    @Test
    fun `Netto inkomen berekenen in 2024`(): Unit = with(TaxYear2024) {
        testCases2024.forEach {
            // Arrange
            val box1 = Inkomstenbelasting(it.brutoloon)

            // Assert
            assertEquals(it.nettoloon, it.brutoloon - box1.calculate())
        }
    }

    private data class BelastbaarInkomenTestCase(
        val belastbaarInkomen: Euro,
        val brutoloon: Euro,
        val nettoloon: Euro
    )
}