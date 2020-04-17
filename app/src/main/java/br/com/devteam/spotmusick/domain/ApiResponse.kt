package br.com.devteam.spotmusick.domain

data class ApiResponse(
    val success: Boolean = true,
    val userMessage: String? = null,
    val techMessage: String? = null
)
