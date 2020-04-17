package br.com.devteam.spotmusick.domain


data class Artist (
    var externalUrls: HashMap<String, String>? = null,

    var href: String? = null,
    var id: String? = null,
    var name: String? = null,
    var type: String? = null,
    var uri: String? = null
)