package com.cocaine.myply.feature.ui.keep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemMypageKeywordBinding

class KeepDetailAdapter: ListAdapter<String, KeepDetailAdapter.KeepDetailViewHolder>(diffUtil) {
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
            binding.keyword = tag
            binding.keywordView.setBackgroundColor(binding.root.resources.getColor(R.color.secondary_brown, null))
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