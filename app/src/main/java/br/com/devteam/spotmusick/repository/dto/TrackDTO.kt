package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Track
import br.com.devteam.spotmusick.utils.listDTOToDomainList

data class TrackDTO(
    var id: String? = null,
    var name: String? = null,
    var href: String? = null,
    var type: String? = null,
    var uri: String? = null,
    var album: AlbumDTO? = null,
    var artists: List<ArtistDTO>? = null
) : DTO<Track> {
    override fun toDomain() =
        Track(
            id,
            name,
            href,
            type,
            uri,
            album!!.toDomain(),
            listDTOToDomainList(artists)
        )
}

