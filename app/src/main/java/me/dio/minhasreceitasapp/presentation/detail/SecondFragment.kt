package me.dio.minhasreceitasapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import me.dio.minhasreceitasapp.R
import me.dio.minhasreceitasapp.databinding.FragmentSecondBinding
import me.dio.minhasreceitasapp.presentation.detail.adapter.ItemListAdapter
import me.dio.minhasreceitasapp.presentation.dialog.DialogEditTextFragment

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val args by navArgs<SecondFragmentArgs>()

    private val viewModel by viewModels<ItemListViewModel> {
        ItemListViewModel.Factory()
    }

    //@TODO Utilizar o concatAdapter para trabalhar apenas com 1 Recycler View
    //Exemplo - #https://medium.com/androiddevelopers/merge-adapters-sequentially-with-mergeadapter-294d2942127a
    private val adapterIngredients by lazy { ItemListAdapter() }
    private val adapterPrepareMode by lazy { ItemListAdapter() }

    private var typeInsert = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observe()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddIngredient.setOnClickListener {
            showDialogNewIngredient()
        }
        binding.btnAddPrepareMode.setOnClickListener {
            showDialogNewPrepareMode()
        }

        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insertIngredientsOrPrepareMode(
                typeInsert = typeInsert,
                name = name,
                idRecipe = args.idRecipe
            )
        }
        adapterIngredients.edit = {
            //@TODO implementar a chamada pelo aluno
        }
        adapterIngredients.remove = {
            //@TODO implementar a chamada pelo aluno
        }

        adapterPrepareMode.edit = {
            //@TODO implementar a chamada pelo aluno
        }

        adapterPrepareMode.remove = {
            //@TODO implementar a chamada pelo aluno
        }
    }

    private fun observe() {
        viewModel.getRecipeWithIngredientsAndPrepareMode(args.idRecipe)
            .observe(viewLifecycleOwner) {
                when (it) {
                    ItemListState.Loading -> {
                        //@TODO mostrar loading para o usuario

                    }
                    is ItemListState.Error -> {
                        //@TODO Mostrar error parar o usuario
                    }
                    is ItemListState.Success -> {
                        adapterIngredients.submitList(it.ingredients)
                        adapterPrepareMode.submitList(it.prepareMode)
                    }
                }
            }
    }

    private fun setupAdapter() {
        binding.rvIngredients.adapter = adapterIngredients
        binding.rvPrepareMode.adapter = adapterPrepareMode
    }

    private fun showDialogNewIngredient() {
        typeInsert = "INGREDIENT"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_ingredient),
            placeHolderText = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }

    private fun showDialogNewPrepareMode() {
        typeInsert = "PREPARE_MODE"
        DialogEditTextFragment.show(
            title = getString(R.string.label_new_prepare_mode),
            placeHolderText = getString(R.string.label_item_description),
            fragmentManager = parentFragmentManager
        )
    }
}
