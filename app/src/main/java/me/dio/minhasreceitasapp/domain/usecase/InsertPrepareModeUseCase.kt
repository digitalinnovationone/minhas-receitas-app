package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class InsertPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareMode: PrepareModeDomain) =
        repository.insertPrepareMode(prepareMode)
}