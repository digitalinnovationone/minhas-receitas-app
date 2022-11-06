package me.dio.minhasreceitasapp.presentation.recipe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.minhasreceitasapp.databinding.ItemRecipeBinding
import me.dio.minhasreceitasapp.domain.model.RecipeDomain

class RecipeAdapter : ListAdapter<RecipeDomain, RecipeAdapter.ViewHolder>(DiffCallback()) {

    var click: (RecipeDomain) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RecipeDomain) {
            binding.tvTitle.text = item.name
            binding.root.setOnClickListener{
                click(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<RecipeDomain>() {
    override fun areItemsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) = oldItem == newItem
    override fun areContentsTheSame(oldItem: RecipeDomain, newItem: RecipeDomain) =
        oldItem.id == newItem.id
}
