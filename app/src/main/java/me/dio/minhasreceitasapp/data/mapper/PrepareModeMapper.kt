package me.dio.minhasreceitasapp.data.mapper

import me.dio.minhasreceitasapp.data.entity.PrepareModeEntity
import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain

fun PrepareModeDomain.toEntity() = PrepareModeEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun PrepareModeEntity.toDomain() = PrepareModeDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)
