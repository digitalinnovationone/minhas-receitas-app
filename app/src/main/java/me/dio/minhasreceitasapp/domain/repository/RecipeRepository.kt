package me.dio.minhasreceitasapp.domain.repository

import me.dio.minhasreceitasapp.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll(): List<RecipeDomain>
    suspend fun insert(recipe: RecipeDomain)
}