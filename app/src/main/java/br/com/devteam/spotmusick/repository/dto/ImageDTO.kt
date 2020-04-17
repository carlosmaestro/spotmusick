package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Image

data class ImageDTO(
    var height: Int? = null,
    var url: String? = null,
    var width: Int? = null
) : DTO<Image> {
    override fun toDomain() =
        Image(
            height,
            url,
            width
        )
}
