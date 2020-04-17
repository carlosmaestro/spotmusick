package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Follower
import br.com.devteam.spotmusick.domain.UserProfile
import br.com.devteam.spotmusick.utils.listDTOToDomainList
import com.google.gson.annotations.SerializedName

data class UserProfileDTO(

    @SerializedName("display_name")
    var displayName: String? = null,

    var email: String? = null,

    @SerializedName("external_urls")
    var externalUrls: HashMap<String, String>? = null,

    var followers: Follower? = null,
    var href: String? = null,
    var id: String? = null,
    var images: List<ImageDTO>? = null,
    var type: String? = null,
    var uri: String? = null,
    var token: String? = null
) : DTO<UserProfile> {
    override fun toDomain() =
        UserProfile(
            token = token,
            displayName = displayName,
            email = email,
            externalRrls = externalUrls,
            followers = followers,
            href = href,
            id = id,
            images = listDTOToDomainList(images),
            type = type,
            uri = uri
        )
}

