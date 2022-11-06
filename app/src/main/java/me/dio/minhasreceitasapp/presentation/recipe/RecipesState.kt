package me.dio.minhasreceitasapp.presentation.recipe

import me.dio.minhasreceitasapp.domain.model.RecipeDomain

sealed interface RecipesState {
    object Loading : RecipesState
    object Empty : RecipesState
    data class Success(val recipes: List<RecipeDomain>) : RecipesState
    data class Error(val message: String) : RecipesState
}
