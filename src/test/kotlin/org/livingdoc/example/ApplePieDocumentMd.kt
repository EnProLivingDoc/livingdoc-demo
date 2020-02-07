package org.livingdoc.example

import org.assertj.core.api.Assertions.assertThat
import org.livingdoc.api.After
import org.livingdoc.api.Before
import org.livingdoc.api.documents.ExecutableDocument
import org.livingdoc.api.fixtures.scenarios.*


@ExecutableDocument("local://ApplePie.md")
class ApplePieDocumentMd {

    @ScenarioFixture
    class ApplePieScenarioFixture {

        private lateinit var ap: ApplePie

        @Before
        fun before() {
            ap = ApplePie()
        }

        @Step("Peter has {a} apples")
        fun init(@Binding("a") a: Float) {
            ap.apples = a
            assertThat(ap.apples).isEqualTo(a)
        }

        @Step.Steps(Step("At the local market he buys {a} more"), Step("From his neighbour he gets {a} more"))
        fun receive(@Binding("a") a: Float) {
            ap.add(a)
        }

        @Step("As he comes home, his siblings secretly take {a}")
        fun remove(@Binding("a") a: Float) {
            ap.remove(a)
        }

        @Step("When he started baking, he noticed he only had {a} left")
        fun result(@Binding("a") a: Float) {
            assertThat(ap.apples).isEqualTo(a)
        }

        @Step("For an apple pie he needs at least {a} apples. Peter is {b} able to make an apple pie")
        fun endResult(@Binding("a") a: Float,
                      @Binding("b") b: String?
        ) {
            val apples = ap.apples - a
            var abletobake = true
            if (b.isNullOrBlank())
                abletobake = false

            if (abletobake)
                assertThat(apples).isGreaterThanOrEqualTo(-3.0f)
            else
                assertThat(apples).isGreaterThanOrEqualTo(0.0f)

        }

        @After
        fun after() {
            println("-- End scenario")
        }

        @After
        fun shutdown() {
            println("-- Shutdown scenario")
        }
    }
}
