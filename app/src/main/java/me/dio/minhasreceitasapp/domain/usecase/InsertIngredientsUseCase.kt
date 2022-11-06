package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.model.IngredientDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class InsertIngredientsUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(ingredientDomain: IngredientDomain) =
        repository.insertIngredient(ingredientDomain)
}