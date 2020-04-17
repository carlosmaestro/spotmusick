package br.com.devteam.spotmusick.domain

data class Search(
    var q: String? = null,
    var type: String? = null,
    var market: String? = null,
    var limit: Int? = null,
    var offset: Int? = null
)