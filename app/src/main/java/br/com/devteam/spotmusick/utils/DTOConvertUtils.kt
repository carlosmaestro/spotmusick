package br.com.devteam.spotmusick.utils

import br.com.devteam.spotmusick.repository.dto.DTO

fun <T> listDTOToDomainList(listDTO: List<DTO<T>>?): List<T> {
    val list = mutableListOf<T>()
    listDTO?.map { item ->
        list.add(item?.toDomain())
    }
    return list.toList()
}