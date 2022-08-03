package com.cocaine.myply.feature.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemMypageEditKeywordBinding
import com.cocaine.myply.databinding.ItemMypageKeywordBinding

class MyPageEditKeywordAdapter :
    ListAdapter<String, MyPageEditKeywordAdapter.MyPageEditKeywordViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyPageEditKeywordViewHolder(private val binding: ItemMypageEditKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keyword: String) {
            binding.keyword = keyword
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageEditKeywordViewHolder {
        val binding = DataBindingUtil.inflate<ItemMypageEditKeywordBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_mypage_edit_keyword,
            parent,
            false
        )
        return MyPageEditKeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageEditKeywordViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
