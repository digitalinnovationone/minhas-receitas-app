package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAll()
}