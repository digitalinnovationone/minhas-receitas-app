package me.dio.minhasreceitasapp.domain.model

typealias IngredientDomain = Ingredient

data class Ingredient(
    val id: Int = 0,
    val name: String,
    val idRecipe: Int
)
