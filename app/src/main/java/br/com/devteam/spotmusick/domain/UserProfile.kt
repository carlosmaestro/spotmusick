package br.com.devteam.spotmusick.domain

data class UserProfile(
    var token: String? = null,
    var displayName: String? = null,
    var email: String? = null,
    var externalRrls: HashMap<String, String>? = null,
    var followers: Follower? = null,
    var href: String? = null,
    var id: String? = null,
    var images: List<Image>? = null,
    var type: String? = null,
    var uri: String? = null
)
