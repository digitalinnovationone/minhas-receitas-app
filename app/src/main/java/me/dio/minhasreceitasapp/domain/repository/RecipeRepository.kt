package me.dio.minhasreceitasapp.domain.repository

import kotlinx.coroutines.flow.Flow
import me.dio.minhasreceitasapp.domain.model.FullRecipeDomain
import me.dio.minhasreceitasapp.domain.model.IngredientDomain
import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll(): Flow<List<RecipeDomain>>
    suspend fun insert(recipe: RecipeDomain)
    suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): FullRecipeDomain
    suspend fun insertIngredient(ingredient: IngredientDomain)
    suspend fun insertPrepareMode(prepareMode: PrepareModeDomain)
    suspend fun updateIngredient(ingredient: IngredientDomain)
    suspend fun updatePrepareMode(prepareMode: PrepareModeDomain)
}
