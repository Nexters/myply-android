package com.cocaine.myply.feature.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemMypageKeywordBinding

class SearchTagAdapter: ListAdapter<String, SearchTagAdapter.KeepDetailViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    class KeepDetailViewHolder(private val binding: ItemMypageKeywordBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: String) {
            val colors = listOf(
                R.color.secondary_red,
                R.color.secondary_yellow,
                R.color.primary_green_light,
                R.color.secondary_blue
            )
            binding.keyword = tag
            binding.keywordView.setBackgroundColor(binding.root.resources.getColor(colors.random(), null))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepDetailViewHolder {
        val binding = DataBindingUtil.inflate<ItemMypageKeywordBinding>(LayoutInflater.from(parent.context), R.layout.item_mypage_keyword, parent,false)
        return KeepDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeepDetailViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}