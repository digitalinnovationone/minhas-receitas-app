package me.dio.minhasreceitasapp.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import me.dio.minhasreceitasapp.data.entity.Ingredient
import me.dio.minhasreceitasapp.data.mapper.toDomain
import me.dio.minhasreceitasapp.data.mapper.toEntity
import me.dio.minhasreceitasapp.domain.model.FullRecipeDomain
import me.dio.minhasreceitasapp.domain.model.IngredientDomain
import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.domain.model.RecipeDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository
import me.dio.recipelist.data.dao.RecipeDao

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getAll(): Flow<List<RecipeDomain>> = withContext(Dispatchers.IO) {
        dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }

    override suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): FullRecipeDomain =
        withContext(Dispatchers.IO) {
            dao.getRecipeWithIngredientsAndPrepareMode(idRecipe).toDomain()
        }

    override suspend fun insertIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(
                Ingredient(
                    id = ingredient.id,
                    name = ingredient.name,
                    idRecipe = ingredient.idRecipe
                )
            )
        }

    override suspend fun insertPrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.insert(prepareMode.toEntity())
        }

    override suspend fun updateIngredient(ingredient: IngredientDomain) =
        withContext(Dispatchers.IO) {
            dao.updateIngredient(ingredient.toEntity())
        }

    override suspend fun updatePrepareMode(prepareMode: PrepareModeDomain) =
        withContext(Dispatchers.IO) {
            dao.updatePrepareMode(prepareMode.toEntity())
        }
}
