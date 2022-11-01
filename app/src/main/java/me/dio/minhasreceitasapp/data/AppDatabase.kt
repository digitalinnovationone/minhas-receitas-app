package me.dio.minhasreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import me.dio.minhasreceitasapp.data.dao.RecipeDao
import me.dio.minhasreceitasapp.data.entity.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}
