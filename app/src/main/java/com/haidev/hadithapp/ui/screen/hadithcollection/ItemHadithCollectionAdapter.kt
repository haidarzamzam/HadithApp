package com.haidev.hadithapp.ui.screen.hadithcollection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haidev.hadithapp.data.model.HadithCollectionModel
import com.haidev.hadithapp.databinding.ItemRowHadithCollectionBinding

class ItemHadithCollectionAdapter(
    private val listener: (HadithCollectionModel.Response.Data.Hadith) -> Unit
) :
    ListAdapter<HadithCollectionModel.Response.Data.Hadith, ItemHadithCollectionAdapter.ViewHolder>(
        DiffCallback()
    ) {

    class ViewHolder private constructor(
        private val binding: ItemRowHadithCollectionBinding,
        private val listener: (HadithCollectionModel.Response.Data.Hadith) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HadithCollectionModel.Response.Data.Hadith) {
            binding.item = item
            binding.ivShare.setOnClickListener {
                listener(item)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: (HadithCollectionModel.Response.Data.Hadith) -> Unit
            ): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRowHadithCollectionBinding.inflate(layoutInflater, parent, false)
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

    class DiffCallback : DiffUtil.ItemCallback<HadithCollectionModel.Response.Data.Hadith>() {
        override fun areItemsTheSame(
            oldItem: HadithCollectionModel.Response.Data.Hadith,
            newItem: HadithCollectionModel.Response.Data.Hadith
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: HadithCollectionModel.Response.Data.Hadith,
            newItem: HadithCollectionModel.Response.Data.Hadith
        ): Boolean {
            return oldItem == newItem
        }
    }
}