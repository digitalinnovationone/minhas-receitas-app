package me.dio.minhasreceitasapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.dio.minhasreceitasapp.data.dao.RecipeDao
import me.dio.minhasreceitasapp.data.mapper.toDomain
import me.dio.minhasreceitasapp.data.mapper.toEntity
import me.dio.minhasreceitasapp.domain.model.RecipeDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {

    override suspend fun getAll(): List<RecipeDomain> = withContext(Dispatchers.IO) {
        dao.getAll().map {
            it.toDomain()
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }

}