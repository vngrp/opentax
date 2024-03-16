package eu.opentax.system.nl

import eu.opentax.domain.TaxYear2023
import eu.opentax.domain.TaxYear2024
import eu.opentax.system.nl.aftrekposten.Arbeidskorting
import eu.opentax.system.nl.aftrekposten.Heffingskorting
import eu.opentax.system.nl.aftrekposten.invoke
import eu.opentax.system.nl.belastingen.BelastbaarInkomen
import eu.opentax.system.nl.belastingen.Inkomstenbelasting
import eu.opentax.system.nl.belastingen.invoke
import kotlin.math.floor
import kotlin.test.Test
import kotlin.test.assertEquals

class InkomstenbelastingTest {
    private val testCases2023 = listOf(
        BelastbaarInkomenTestCase(56_000.00, 56_000.00, 40_219.00),
        BelastbaarInkomenTestCase(200_000.00, 200_000.00, 110_181.00),
        BelastbaarInkomenTestCase(0.00, 0.00, 0.00),
    )

    private val testCases2024 = listOf(
        BelastbaarInkomenTestCase(56_000.00, 56_000.00, 41_080.00),
        BelastbaarInkomenTestCase(200_000.00, 200_000.00, 110_463.00),
        BelastbaarInkomenTestCase(0.00, 0.00, 0.00),
    )

    @Test
    fun `Netto inkomen berekenen in 2023`(): Unit = with(TaxYear2023) {
        testCases2023.forEach {
            // Arrange
            val belastbaar = BelastbaarInkomen(it.brutoloon)
            val box1 = Inkomstenbelasting(belastbaar)
            val heffingskorting = Heffingskorting(belastbaar)
            val arbeidskorting = Arbeidskorting(belastbaar)

            // Act
            val netto = it.brutoloon - floor(box1() - heffingskorting() - arbeidskorting()).coerceAtLeast(0.0)

            // Assert
            assertEquals(it.nettoloon, netto.coerceAtLeast(0.0))
        }
    }

    @Test
    fun `Netto inkomen berekenen in 2024`(): Unit = with(TaxYear2024) {
        testCases2024.forEach {
            // Arrange
            val belastbaar = BelastbaarInkomen(it.brutoloon)
            val box1 = Inkomstenbelasting(belastbaar)
            val heffingskorting = Heffingskorting(belastbaar)
            val arbeidskorting = Arbeidskorting(belastbaar)

            // Act
            val netto = it.brutoloon - floor(box1() - heffingskorting() - arbeidskorting()).coerceAtLeast(0.0)

            // Assert
            assertEquals(it.nettoloon, netto.coerceAtLeast(0.0))
        }
    }

    private data class BelastbaarInkomenTestCase(
        val belastbaarInkomen: Euro,
        val brutoloon: Euro,
        val nettoloon: Euro
    )
}

fun main(): Unit = with(TaxYear2023) {
    val bruto = BelastbaarInkomen(56_000.00)

    println("Inkomstenbelasting")
    println("-------------------")
    println("Bruto inkomen: € ${"%,d".format(bruto().toInt())},-")
    println("Loonheffing: € ${"%,d".format(Inkomstenbelasting(bruto)().toInt())},-")
    println("-------------------")
    println("Tussenresultaat: € ${"%,d".format((bruto() - Inkomstenbelasting(bruto)()).toInt())},-")
    println("Heffingskorting: € ${"%,d".format(Heffingskorting(bruto)().toInt())},-")
    println("Arbeidskorting: € ${"%,d".format(Arbeidskorting(bruto)().toInt())},-")
    println("-------------------")
    println("Netto inkomen: € ${"%,d".format((bruto() - floor(Inkomstenbelasting(bruto)() - Heffingskorting(bruto)() - Arbeidskorting(bruto)()).coerceAtLeast(0.0)).toInt())},-")
}