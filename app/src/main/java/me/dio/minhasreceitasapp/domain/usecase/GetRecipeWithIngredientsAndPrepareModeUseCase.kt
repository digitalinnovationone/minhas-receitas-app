package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class GetRecipeWithIngredientsAndPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(idRecipe: Int) =
        repository.getRecipeWithIngredientsAndPrepareMode(idRecipe)
}
