package br.com.devteam.spotmusick.domain

import java.io.Serializable

data class Profile(
    var email: String? = null,
    var name: String? = null,
    var phone: String? = null
): Serializable