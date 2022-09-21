package com.cocaine.myply.feature.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemMypageKeywordBinding

class MyPageKeywordAdapter :
    ListAdapter<String, MyPageKeywordAdapter.MyPageKeywordViewHolder>(diffUtil) {

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

    class MyPageKeywordViewHolder(private val binding: ItemMypageKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val keywordColorList = listOf(
            R.color.secondary_red,
            R.color.secondary_yellow,
            R.color.primary_green_light,
            R.color.secondary_blue
        )

        fun bind(keyword: String) {
            binding.keyword = keyword

            binding.keywordView.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    keywordColorList.random()
                )
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageKeywordViewHolder {
        val binding = DataBindingUtil.inflate<ItemMypageKeywordBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_mypage_keyword,
            parent,
            false
        )
        return MyPageKeywordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageKeywordViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
