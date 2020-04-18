package br.com.devteam.spotmusick.domain

import java.io.Serializable


data class Album(

    var albumType: String? = null,

    val artists: List<Artist>? = null,

    var availableMarkets: List<String>? = null,

    var externalUrls: HashMap<String, String>? = null,

    var href: String? = null,
    var id: String? = null,
    var images: List<Image>? = null,
    var name: String? = null,
    var type: String? = null,
    var uri: String? = null
) : Serializable

