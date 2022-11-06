package me.dio.minhasreceitasapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

typealias FullRecipeEntity = FullRecipe

data class FullRecipe(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val ingredients: List<Ingredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val prepareMode: List<PrepareMode>
)
