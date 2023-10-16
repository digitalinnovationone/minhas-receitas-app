package me.dio.recipelist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import me.dio.minhasreceitasapp.data.entity.FullRecipeEntity
import me.dio.minhasreceitasapp.data.entity.Ingredient
import me.dio.minhasreceitasapp.data.entity.PrepareMode
import me.dio.minhasreceitasapp.data.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe")
    fun getAll(): Flow<List<RecipeEntity>>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insert(ingredient: Ingredient)

    @Insert
    fun insert(prepareMode: PrepareMode)

    @Transaction
    @Query("SELECT * From recipe where id = :recipeId")
    fun getRecipeWithIngredientsAndPrepareMode(recipeId: Int): FullRecipeEntity

    @Update
    fun updateIngredient(ingredient: Ingredient)
    @Delete
    fun deleteIngredient(ingredient: Ingredient)

    @Update
    fun updatePrepareMode(prepareMode: PrepareMode)
    @Delete
    fun deletePrepareMode(prepareMode: PrepareMode)
}
