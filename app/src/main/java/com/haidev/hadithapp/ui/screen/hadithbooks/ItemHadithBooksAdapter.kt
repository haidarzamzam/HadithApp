package com.haidev.hadithapp.ui.screen.hadithbooks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haidev.hadithapp.data.model.HadithBooksModel
import com.haidev.hadithapp.databinding.ItemRowHadithBooksBinding

class ItemHadithBooksAdapter(
    private val listener: (HadithBooksModel.Response.Data) -> Unit
) :
    ListAdapter<HadithBooksModel.Response.Data, ItemHadithBooksAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder private constructor(
        private val binding: ItemRowHadithBooksBinding,
        private val listener: (HadithBooksModel.Response.Data) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HadithBooksModel.Response.Data) {
            binding.item = item
            itemView.setOnClickListener {
                listener(item)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: (HadithBooksModel.Response.Data) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRowHadithBooksBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, listener)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DiffCallback : DiffUtil.ItemCallback<HadithBooksModel.Response.Data>() {
        override fun areItemsTheSame(
            oldItem: HadithBooksModel.Response.Data,
            newItem: HadithBooksModel.Response.Data
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HadithBooksModel.Response.Data,
            newItem: HadithBooksModel.Response.Data
        ): Boolean {
            return oldItem == newItem
        }
    }
}