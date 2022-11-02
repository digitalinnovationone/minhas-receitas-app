package me.dio.minhasreceitasapp.presentation.recipe

import me.dio.minhasreceitasapp.domain.model.RecipeDomain

sealed interface RecipeState {
    object Loading : RecipeState
    object Empty : RecipeState
    data class Success(val recipe: List<RecipeDomain>) : RecipeState
    data class Error(val message: String) : RecipeState
}