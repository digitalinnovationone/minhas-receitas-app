package me.dio.minhasreceitasapp.domain.usecase

import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.domain.repository.RecipeRepository

class DeletePrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareModeDomain: PrepareModeDomain) =
        repository.deletePrepareMode(prepareModeDomain)
}