package br.com.devteam.spotmusick.domain

data class PagingObject<T>(
    var href: String? = null,
    var items: List<T>? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var total: Int? = null
)