package br.com.devteam.spotmusick.domain

import java.io.Serializable

data class Follower(
    var href: String? = null,
    var total: Int? = null
): Serializable