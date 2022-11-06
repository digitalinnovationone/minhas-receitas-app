package me.dio.minhasreceitasapp.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import me.dio.minhasreceitasapp.data.db
import me.dio.minhasreceitasapp.data.repository.RecipeRepositoryImpl
import me.dio.minhasreceitasapp.domain.model.RecipeDomain
import me.dio.minhasreceitasapp.domain.usecase.GetAllRecipesUseCase
import me.dio.recipelist.domain.usecase.InsertRecipeUseCase

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase
) : ViewModel() {

    private val _state = MutableSharedFlow<RecipesState>()
    val state: SharedFlow<RecipesState> = _state

    init {
        getAllRecipes()
    }
    
    private fun getAllRecipes() = viewModelScope.launch {
        getAllRecipesUseCase()
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(RecipesState.Loading)
            }.catch {
                _state.emit(RecipesState.Error("erro"))
            }.collect { recipes ->
                if (recipes.isEmpty()) {
                    _state.emit(RecipesState.Empty)
                } else {
                    _state.emit(RecipesState.Success(recipes))
                }
            }
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name))
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application =
                checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return RecipesViewModel(
                getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                insertRecipeUseCase = InsertRecipeUseCase(repository)
            ) as T
        }
    }

}