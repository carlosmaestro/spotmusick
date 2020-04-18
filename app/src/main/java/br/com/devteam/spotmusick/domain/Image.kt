package br.com.devteam.spotmusick.domain

import java.io.Serializable

data class Image(
    var height: Int? = null,
    var url: String? = null,
    var width: Int? = null
): Serializable