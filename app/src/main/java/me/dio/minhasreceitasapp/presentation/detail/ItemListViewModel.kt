package me.dio.minhasreceitasapp.presentation.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch
import me.dio.minhasreceitasapp.data.db
import me.dio.minhasreceitasapp.data.repository.RecipeRepositoryImpl
import me.dio.minhasreceitasapp.domain.model.IngredientDomain
import me.dio.minhasreceitasapp.domain.model.PrepareModeDomain
import me.dio.minhasreceitasapp.domain.usecase.DeleteIngredientsUseCase
import me.dio.minhasreceitasapp.domain.usecase.DeletePrepareModeUseCase
import me.dio.minhasreceitasapp.domain.usecase.GetRecipeWithIngredientsAndPrepareModeUseCase
import me.dio.minhasreceitasapp.domain.usecase.InsertIngredientsUseCase
import me.dio.minhasreceitasapp.domain.usecase.InsertPrepareModeUseCase
import me.dio.minhasreceitasapp.domain.usecase.UpdateIngredientsUseCase
import me.dio.minhasreceitasapp.domain.usecase.UpdatePrepareModeUseCase
import me.dio.minhasreceitasapp.presentation.mapper.toModelIngredient
import me.dio.minhasreceitasapp.presentation.mapper.toModelPrepareMode
import me.dio.minhasreceitasapp.presentation.model.ItemListModel

class ItemListViewModel(
    private val getRecipeWithIngredientsAndPrepareModeUseCase: GetRecipeWithIngredientsAndPrepareModeUseCase,
    private val insertIngredientsUseCase: InsertIngredientsUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase,
    private val updateIngredientsUseCase: UpdateIngredientsUseCase,
    private val updatePrepareModeUseCase: UpdatePrepareModeUseCase,
    private val deleteIngredientUseCase: DeleteIngredientsUseCase,
    private val deletePrepareModeUseCase: DeletePrepareModeUseCase,
) : ViewModel() {

    fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): LiveData<ItemListState> = liveData {
        emit(ItemListState.Loading)
        val state = try {
            val fullRecipe = getRecipeWithIngredientsAndPrepareModeUseCase(idRecipe)
            ItemListState.Success(
                ingredients = fullRecipe.ingredients.toModelIngredient(),
                prepareMode = fullRecipe.prepareMode.toModelPrepareMode()
            )
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            ItemListState.Error(exception.message.toString())
        }
        emit(state)
    }

    fun insertIngredientsOrPrepareMode(
        typeInsert: String,
        name: String,
        idRecipe: Int
    ) = viewModelScope.launch {
        if (typeInsert == "INGREDIENT") {
            insertIngredientsUseCase(
                IngredientDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        } else {
            insertPrepareModeUseCase(
                PrepareModeDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }

    fun updateIngredientsOrPrepareMode(
        itemList: ItemListModel,
        typeInsert: String,
        name: String,
        idRecipe: Int
    ) = viewModelScope.launch {
        if (typeInsert == "UPDATE_INGREDIENT") {
            updateIngredientsUseCase(
                IngredientDomain(
                    id = itemList.id,
                    idRecipe = idRecipe,
                    name = name,
                )
            )
        } else {
            updatePrepareModeUseCase(
                PrepareModeDomain(
                    id = itemList.id,
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }
    fun removeIngredientOrPrepareMode(
        typeInsert: String,
        name: String,
        id: Int,
        idRecipe: Int
    ) = viewModelScope.launch {
        if (typeInsert == "REMOVE_INGREDIENT") {
            deleteIngredientUseCase(
                IngredientDomain(
                    id = id,
                    name = name,
                    idRecipe = idRecipe
                )
            )
        } else {
            deletePrepareModeUseCase(
                PrepareModeDomain(
                    id = id,
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }

    class Factory : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return ItemListViewModel(
                getRecipeWithIngredientsAndPrepareModeUseCase = GetRecipeWithIngredientsAndPrepareModeUseCase(
                    repository
                ),
                insertIngredientsUseCase = InsertIngredientsUseCase(repository),
                insertPrepareModeUseCase = InsertPrepareModeUseCase(repository),
                updateIngredientsUseCase = UpdateIngredientsUseCase(repository),
                updatePrepareModeUseCase = UpdatePrepareModeUseCase(repository),
                deleteIngredientUseCase = DeleteIngredientsUseCase(repository),
                deletePrepareModeUseCase = DeletePrepareModeUseCase(repository)
            ) as T
        }
    }

}