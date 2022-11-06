package me.dio.minhasreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.recipelist.data.dao.RecipeDao
import me.dio.minhasreceitasapp.data.entity.Ingredient
import me.dio.minhasreceitasapp.data.entity.PrepareMode
import me.dio.minhasreceitasapp.data.entity.Recipe

@Database(
    entities = [
        Recipe::class,
        Ingredient::class,
        PrepareMode::class
    ], version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
