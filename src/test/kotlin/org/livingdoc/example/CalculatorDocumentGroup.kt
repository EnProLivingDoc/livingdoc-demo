package org.livingdoc.example

import org.assertj.core.api.Assertions
import org.livingdoc.api.After
import org.livingdoc.api.Before
import org.livingdoc.api.documents.ExecutableDocument
import org.livingdoc.api.documents.Group
import org.livingdoc.api.fixtures.decisiontables.Check
import org.livingdoc.api.fixtures.decisiontables.DecisionTableFixture
import org.livingdoc.api.fixtures.decisiontables.Input
import org.livingdoc.api.fixtures.scenarios.Binding
import org.livingdoc.api.fixtures.scenarios.ScenarioFixture
import org.livingdoc.api.fixtures.scenarios.Step
import org.livingdoc.example.GroupedDocuments.Companion.sut

@Group
class GroupedDocuments {

    @ExecutableDocument("local://Calculator.md")
    class GroupedDocument1 {
        @DecisionTableFixture
        class CalculatorDecisionTableFixture {

            @Input("a")
            private var valueA: Float = 0f
            private var valueB: Float = 0f

            @Input("b")
            fun setValueB(valueB: Float) {
                this.valueB = valueB
            }

            @Check("a + b = ?")
            fun checkSum(expectedValue: Float) {
                val result = sut.sum(valueA, valueB)
                Assertions.assertThat(result).isEqualTo(expectedValue)
            }

            @Check("a - b = ?")
            fun checkDiff(expectedValue: Float) {
                val result = sut.diff(valueA, valueB)
                Assertions.assertThat(result).isEqualTo(expectedValue)
            }

            @Check("a * b = ?")
            fun checkMultiply(expectedValue: Float) {
                val result = sut.multiply(valueA, valueB)
                Assertions.assertThat(result).isEqualTo(expectedValue)
            }

            @Check("a / b = ?")
            fun checkDivide(expectedValue: Float) {
                val result = sut.divide(valueA, valueB)
                Assertions.assertThat(result).isEqualTo(expectedValue)
            }
        }
    }

    @ExecutableDocument("local://Calculator.md")
    class GroupedDocument2 {
        @ScenarioFixture
        class CalculatorScenarioFixture {

            @Step("adding {a} and {b} equals {c}")
            fun add(
                    @Binding("a") a: Float,
                    @Binding("b") b: Float,
                    @Binding("c") c: Float
            ) {
                val result = sut.sum(a, b)
                Assertions.assertThat(result).isEqualTo(c)
            }

            @Step("subtraction {b} form {a} equals {c}")
            fun subtract(
                    @Binding("a") a: Float,
                    @Binding("b") b: Float,
                    @Binding("c") c: Float
            ) {
                val result = sut.diff(a, b)
                Assertions.assertThat(result).isEqualTo(c)
            }
        }
    }

    companion object {
        lateinit var sut: Calculator

        @JvmStatic
        @Before
        fun setUp() {
            sut = Calculator()
            println("Before group of documents")
        }

        @JvmStatic
        @After
        fun cleanUp() {
            println("After group of documents")
        }
    }
}

@ExecutableDocument("local://Calculator.md", group = GroupedDocuments::class)
class GroupedDocument3 {

    @ScenarioFixture
    class ScenarioTests {

        @Step("multiplying {a} and {b} equals {c}")
        fun multiply(
                @Binding("a") a: Float,
                @Binding("b") b: Float,
                @Binding("c") c: Float
        ) {
            val result = sut.multiply(a, b)
            Assertions.assertThat(result).isEqualTo(c)
        }

        @Step("dividing {a} by {b} equals {c}")
        fun divide(
                @Binding("a") a: Float,
                @Binding("b") b: Float,
                @Binding("c") c: Float
        ) {
            val result = sut.divide(a, b)
            Assertions.assertThat(result).isEqualTo(c)
        }
    }
}
