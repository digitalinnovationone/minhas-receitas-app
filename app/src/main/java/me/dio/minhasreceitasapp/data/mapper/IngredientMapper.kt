package me.dio.minhasreceitasapp.data.mapper

import me.dio.minhasreceitasapp.data.entity.IngredientEntity
import me.dio.minhasreceitasapp.domain.model.IngredientDomain

fun IngredientDomain.toEntity() = IngredientEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun IngredientEntity.toDomain() = IngredientDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)
