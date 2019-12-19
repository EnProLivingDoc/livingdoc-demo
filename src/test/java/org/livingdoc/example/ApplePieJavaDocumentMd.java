package org.livingdoc.example;

import org.livingdoc.api.After;
import org.livingdoc.api.Before;
import org.livingdoc.api.documents.ExecutableDocument;
import org.livingdoc.api.fixtures.scenarios.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExecutableDocument("local://ApplePie.md")
public class ApplePieJavaDocumentMd {

    @ScenarioFixture("ApplePie")
    public static class ApplePieScenarioFixture {

        private ApplePie ap;

        @Before
        void before() {
            ap = new ApplePie();
        }

        @Step("Peter has {a} apples")
        void init(@Binding("a") Float a) {
            ap.setApples(a);
            assertThat(a).isEqualTo(ap.getApples());
        }

        @Step("At the local market he buys {a} more")
        @Step("From his neighbour he gets {a} more")
        void receive(@Binding("a") Float a) {
            ap.add(a);
        }

        @Step("As he comes home, his siblings secretly take {a}")
        void remove(@Binding("a") Float a) {
            ap.remove(a);
        }

        @Step("When he started baking, he noticed he only had {a} left")
        void result(@Binding("a") Float a) {
            assertEquals(ap.getApples(), a);
        }

        @Step("For an apple pie he needs at least {a} apples. Peter is able to make an apple pie")
        void endResult(@Binding("a") Float a) {
            float apples = ap.getApples() - a;
            assertThat(apples).isGreaterThanOrEqualTo(0);
        }

        @After
        void after(){
            System.out.println("-- End scenario with value " + ap.getApples());
        }

        @After
        void shutdown(){
            System.out.println("-- Shutdown scenario");
        }
    }
}
