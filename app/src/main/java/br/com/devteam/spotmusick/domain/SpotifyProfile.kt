package br.com.devteam.spotmusick.domain

data class SpotifyProfile(
    var id: String? = null,
    var displayName: String? = null,
    var email: String? = null,
    var avatar: String? = null,
    var accessToken: String? = null
)