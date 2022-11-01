package me.dio.minhasreceitasapp.data.mapper

import me.dio.minhasreceitasapp.data.entity.RecipeEntity
import me.dio.minhasreceitasapp.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name
)
