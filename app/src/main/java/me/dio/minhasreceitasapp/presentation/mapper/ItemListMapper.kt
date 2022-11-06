package me.dio.minhasreceitasapp.presentation.mapper

import me.dio.minhasreceitasapp.domain.model.IngredientDomain
import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.presentation.model.ItemListModel

fun IngredientDomain.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<IngredientDomain>.toModelIngredient() = map {
    it.toModel()
}

fun PrepareModeDomain.toModel() = ItemListModel(
    id = id,
    name = name
)

fun List<PrepareModeDomain>.toModelPrepareMode() = map {
    it.toModel()
}


