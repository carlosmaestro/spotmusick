package br.com.devteam.spotmusick.repository.dto

import br.com.devteam.spotmusick.domain.PagingObject
import br.com.devteam.spotmusick.utils.listDTOToDomainList

data class PagingObjectDTO<T, E>(
    var href: String? = null,
    var items: List<T>? = null,
    var limit: Int? = null,
    var offset: Int? = null,
    var next: String? = null,
    var previous: String? = null,
    var total: Int? = null
) {
    fun toDomain(): PagingObject<E>{

        val listDTO = items as List<DTO<E>>

        val list = mutableListOf<E>()
        listDTO?.map { item ->
            list.add(item.toDomain())
        }

        return PagingObject(
            href,
            list.toList(),
            limit,
            offset,
            next,
            previous,
            total
        )
    }
}

