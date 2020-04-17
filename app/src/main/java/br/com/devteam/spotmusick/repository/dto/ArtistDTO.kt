package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Artist
import com.google.gson.annotations.SerializedName

data class ArtistDTO(
    @SerializedName("external_urls")
    var externalUrls: HashMap<String, String>? = null,

    var href: String? = null,
    var id: String? = null,
    var name: String? = null,
    var type: String? = null,
    var uri: String? = null
) : DTO<Artist> {
    override fun toDomain() =
        Artist(
            externalUrls = externalUrls,
            href = href,
            id = id,
            name = name,
            type = type,
            uri = uri
        )
}