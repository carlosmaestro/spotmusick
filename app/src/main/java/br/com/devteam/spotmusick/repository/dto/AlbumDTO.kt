package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Album
import br.com.devteam.spotmusick.utils.listDTOToDomainList
import com.google.gson.annotations.SerializedName

data class AlbumDTO(

    @SerializedName("album_type")
    var albumType: String? = null,

    val artists: List<ArtistDTO>? = null,

    @SerializedName("available_markets")
    var availableMarkets: List<String>? = null,

    @SerializedName("external_urls")
    var externalUrls: HashMap<String, String>? = null,

    var href: String? = null,
    var id: String? = null,
    var images: List<ImageDTO>? = null,
    var name: String? = null,
    var type: String? = null,
    var uri: String? = null
): DTO<Album>{
    override fun toDomain() =
        Album(
            albumType,
            listDTOToDomainList(artists),
            availableMarkets,
            externalUrls,
            href,
            id,
            listDTOToDomainList(images),
            name,
            type,
            uri
        )
}

