package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.Follower

data class FollowerDTO(
    var href: String? = null,
    var total: Int? = null
)

fun FollowerDTO.toFollower() =
    Follower(
        href,
        total
    )