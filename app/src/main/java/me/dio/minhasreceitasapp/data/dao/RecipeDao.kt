package me.dio.minhasreceitasapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.dio.minhasreceitasapp.data.entity.RecipeEntity

@Dao
interface RecipeDao {
    @Query("Select * FROM recipe")
    fun getAll(): List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity)
}
