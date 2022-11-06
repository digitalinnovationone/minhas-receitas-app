package me.dio.minhasreceitasapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias IngredientEntity = Ingredient

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "idRecipe") val idRecipe: Int
)