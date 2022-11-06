package me.dio.minhasreceitasapp.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.minhasreceitasapp.databinding.ItemFullRecipeBinding
import me.dio.minhasreceitasapp.presentation.model.ItemListModel

class ItemListAdapter : ListAdapter<ItemListModel, ItemListAdapter.ViewHolder>(DiffCallback()) {

    var edit: (ItemListModel) -> Unit = {}
    var remove: (ItemListModel) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFullRecipeBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemFullRecipeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ItemListModel) = with(binding) {
            cbCheck.text = item.name
            btnEdit.setOnClickListener {
                edit(item)
            }
            btnRemove.setOnClickListener {
                remove(item)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<ItemListModel>() {
    override fun areItemsTheSame(oldItem: ItemListModel, newItem: ItemListModel) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ItemListModel, newItem: ItemListModel) =
        oldItem.id == newItem.id
}
