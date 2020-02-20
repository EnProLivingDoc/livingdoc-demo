package org.livingdoc.example

class SearchEngine {

    private var engine: String? = null
    private var environment: String? = null
    private var searchphrase: String? = null
    private val related = mutableListOf<String>()

    fun setEnvironment(environment: String) {
        this.environment = environment
    }

    fun setEngine(searchengine: String?) {
        this.engine = searchengine
    }

    fun getEngine(): String? {
        return this.engine
    }

    fun getEnvironment(): String? {
        return this.environment
    }

    fun setSearchPhrase(phrase: String) {
        this.searchphrase = phrase
    }

    fun getSearchPhrase(): String? {
        return this.searchphrase
    }

    fun addRelated(related: String) {
        this.related.add(related)
    }

    fun getRelated(): List<String> {
        return this.related
    }
}