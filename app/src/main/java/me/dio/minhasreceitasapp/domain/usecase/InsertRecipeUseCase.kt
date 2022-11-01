package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.model.RecipeDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}