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

            @Step("multiplying {a} and {b} equals {c}")
            fun multiply(
                    @Binding("a") a: Float,
                    @Binding("b") b: Float,
                    @Binding("c") c: Float
            ) {
                val result = sut.multiply(a, b)
                Assertions.assertThat(result).isEqualTo(c)
            }
        }
    }

    @ExecutableDocument("local://Calculator.md")
    class GroupedDocument2 {
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

            @Step("multiplying {a} and {b} equals {c}")
            fun multiply(
                    @Binding("a") a: Float,
                    @Binding("b") b: Float,
                    @Binding("c") c: Float
            ) {
                val result = sut.multiply(a, b)
                Assertions.assertThat(result).isEqualTo(c)
            }
        }
    }
}

@ExecutableDocument("local://Calculator.md", group = GroupedDocuments::class)
class GroupedDocument3 {
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

        @Step("multiplying {a} and {b} equals {c}")
        fun multiply(
                @Binding("a") a: Float,
                @Binding("b") b: Float,
                @Binding("c") c: Float
        ) {
            val result = sut.multiply(a, b)
            Assertions.assertThat(result).isEqualTo(c)
        }
    }
}
