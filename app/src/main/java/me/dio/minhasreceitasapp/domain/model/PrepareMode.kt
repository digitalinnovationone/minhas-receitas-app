package me.dio.minhasreceitasapp.domain.model

typealias PrepareModeDomain = PrepareMode

data class PrepareMode(
    val id: Int = 0,
    val name: String,
    val idRecipe: Int
)
