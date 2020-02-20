package org.livingdoc.example

import org.assertj.core.api.Assertions.assertThat
import org.livingdoc.api.Before

import org.livingdoc.api.documents.ExecutableDocument
import org.livingdoc.api.fixtures.scenarios.Binding
import org.livingdoc.api.fixtures.scenarios.ScenarioFixture
import org.livingdoc.api.fixtures.scenarios.Step


@ExecutableDocument("local://Gherkin.html")
class GherkinHtml {

    @ScenarioFixture
    class CalculatorDecisionTableFixture {

        private lateinit var se: SearchEngine

        @Before
        fun init() {
            se = SearchEngine()
        }

        @Step("{environment} is on the {searchengine} page")
        fun given(@Binding("environment") environment: String,
                  @Binding("searchengine") searchengine: String) {
            se.setEnvironment(environment)
            se.setEngine(searchengine)

            assertThat(se.getEngine()).isEqualTo(searchengine).isNotNull()
            assertThat(se.getEnvironment()).isEqualTo(environment).isNotNull()
        }

        @Step("the search phrase {phrase} is entered")
        fun `when`(@Binding("phrase") phrase: String) {
            se.setSearchPhrase(phrase)

            assertThat(se.getSearchPhrase()).isEqualTo(phrase)
        }

        @Step("results for {phrase} are {not} shown")
        fun `then`(@Binding("phrase") phrase: String, @Binding("not") semantics: String?) {

            //the phrases are equal -> no "not"
            var negation = true
            //phrases are not equal -> there must be a not
            if (semantics.isNullOrEmpty())
                negation = false


            assertThat(!phrase.equals(se.getSearchPhrase())).isEqualTo(negation)
        }

        @Step("the related results include {related}")
        fun `then and`(@Binding("related") related: String) {
            se.addRelated(related)

            assertThat(se.getRelated()).isNotEmpty
        }
    }
}