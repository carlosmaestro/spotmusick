package br.com.devteam.spotmusick.domain


data class Track(
    var id: String? = null,
    var name: String? = null,
    var href: String? = null,
    var type: String? = null,
    var uri: String? = null,
    var album: Album,
    var artists: List<Artist>
)