package me.dio.minhasreceitasapp.data.mapper

import me.dio.minhasreceitasapp.data.entity.FullRecipeEntity
import me.dio.minhasreceitasapp.domain.model.FullRecipeDomain
import me.dio.minhasreceitasapp.domain.model.IngredientDomain

fun FullRecipeEntity.toDomain() = FullRecipeDomain(
    recipe = recipe.toDomain(),
    ingredients = ingredients.map {
        IngredientDomain(
            id = it.id,
            name = it.name,
            idRecipe = it.idRecipe
        )
    },
    prepareMode = prepareMode.map {
        it.toDomain()
    }
)