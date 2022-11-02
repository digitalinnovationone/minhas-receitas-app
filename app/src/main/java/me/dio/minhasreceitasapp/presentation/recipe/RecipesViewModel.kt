package me.dio.minhasreceitasapp.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import me.dio.minhasreceitasapp.data.db
import me.dio.minhasreceitasapp.data.repository.RecipeRepositoryImpl
import me.dio.minhasreceitasapp.domain.model.RecipeDomain
import me.dio.minhasreceitasapp.domain.usecase.GetAllRecipesUseCase
import me.dio.minhasreceitasapp.domain.usecase.InsertRecipeUseCase

class RecipesViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase
) : ViewModel() {

    val state: LiveData<RecipeState> = liveData {
        emit(RecipeState.Loading)
        val state = try {
            val recipes = getAllRecipesUseCase()
            if (recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            RecipeState.Error(exception.message.toString())
        }
        emit(state)
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