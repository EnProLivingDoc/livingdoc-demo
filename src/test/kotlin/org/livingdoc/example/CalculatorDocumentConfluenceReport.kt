package org.livingdoc.example

import org.assertj.core.api.Assertions.assertThat
import org.livingdoc.api.Before
import org.livingdoc.api.documents.ExecutableDocument
import org.livingdoc.api.fixtures.decisiontables.BeforeRow
import org.livingdoc.api.fixtures.decisiontables.Check
import org.livingdoc.api.fixtures.decisiontables.DecisionTableFixture
import org.livingdoc.api.fixtures.decisiontables.Input
import org.livingdoc.api.fixtures.scenarios.Binding
import org.livingdoc.api.fixtures.scenarios.ScenarioFixture
import org.livingdoc.api.fixtures.scenarios.Step

@ExecutableDocument("confluence-1://327693")
class CalculatorDocumentConfluenceReport {

    @ScenarioFixture
    class CalculatorScenarioFixture {

        private lateinit var sut: Calculator

        @Before
        fun before() {
            sut = Calculator()
        }

        @Step("adding {a} and {b} equals {c}")
        fun add(
                @Binding("a") a: Float,
                @Binding("b") b: Float,
                @Binding("c") c: Float
        ) {
            val result = sut.sum(a, b)
            assertThat(result).isEqualTo(c)
        }
    }

}
