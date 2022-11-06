package me.dio.minhasreceitasapp.domain.model

typealias FullRecipeDomain = FullRecipe

data class FullRecipe(
    val recipe: RecipeDomain,
    val ingredients: List<IngredientDomain>,
    val prepareMode: List<PrepareModeDomain>
)
