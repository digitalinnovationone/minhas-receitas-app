package me.dio.minhasreceitasapp.presentation.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import me.dio.minhasreceitasapp.R
import me.dio.minhasreceitasapp.databinding.FragmentFirstBinding
import me.dio.minhasreceitasapp.presentation.dialog.DialogEditTextFragment
import me.dio.minhasreceitasapp.presentation.recipe.adapter.RecipeAdapter

class FirstFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels {
        RecipesViewModel.Factory()
    }

    private lateinit var binding: FragmentFirstBinding
    private val adapter by lazy { RecipeAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        setupAdapter()
        observeStates()
    }

    private fun observeStates() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                RecipesState.Loading -> {
                    binding.pbLoading.isVisible = true
                }
                RecipesState.Empty -> {
                    binding.pbLoading.isVisible = false
                    //@TODO fazer uma view empty state pra ficar bonito
                    Toast.makeText(
                        requireContext(),
                        "Ops, nao temos nenhuma receita.",
                        Toast.LENGTH_LONG
                    ).show()
                }
                is RecipesState.Success -> {
                    binding.pbLoading.isVisible = false
                    adapter.submitList(it.recipes)

                }
                is RecipesState.Error -> {
                    binding.pbLoading.isVisible = false
                }
            }
        }
    }



    private fun setupListeners() {
        binding.fabInsertRecipe.setOnClickListener {
            showDialog()
        }

        setFragmentResultListener(DialogEditTextFragment.FRAGMENT_RESULT) { requestKey, bundle ->
            val name = bundle.getString(DialogEditTextFragment.EDIT_TEXT_VALUE) ?: ""
            viewModel.insert(name)
        }

        adapter.click = { recipeItem ->
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(
                recipeItem.id
            )
            findNavController().navigate(action)
        }
    }

    private fun setupAdapter() {
        binding.rvRecipes.adapter = adapter
    }

    private fun showDialog() {
        DialogEditTextFragment.show(
            title = getString(R.string.title_new_recipe),
            placeHolderText = getString(R.string.label_name_recipe),
            fragmentManager = parentFragmentManager
        )
    }

    fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
        owner.lifecycleScope.launch {
            owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@observe.collect(observe)
            }
        }
    }
}