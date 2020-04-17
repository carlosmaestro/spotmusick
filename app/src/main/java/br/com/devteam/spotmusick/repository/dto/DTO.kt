package br.com.devteam.spotmusick.repository.dto

interface  DTO<T> {
     fun toDomain(): T
}