package com.cocaine.myply.feature.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemMypageEditKeywordBinding

class MyPageEditKeywordAdapter(private val clickListener: (String) -> (Unit)) :
    ListAdapter<Pair<String, Boolean>, MyPageEditKeywordAdapter.MyPageEditKeywordViewHolder>(
        diffUtil
    ) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Pair<String, Boolean>>() {
            override fun areItemsTheSame(
                oldItem: Pair<String, Boolean>,
                newItem: Pair<String, Boolean>
            ): Boolean {
                return oldItem.first == newItem.first
            }

            override fun areContentsTheSame(
                oldItem: Pair<String, Boolean>,
                newItem: Pair<String, Boolean>
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class MyPageEditKeywordViewHolder(private val binding: ItemMypageEditKeywordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(keywordAndClicked: Pair<String, Boolean>, clickListener: (String) -> Unit) {
            val (keyword, clicked) = keywordAndClicked
            binding.keyword = keyword
            binding.clicked = clicked

            binding.keywordView.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    if (clicked) R.color.secondary_red else R.color.gray_30
                )
            )

            binding.root.setOnClickListener {
                clickListener(keyword)
            }
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
        holder.bind(currentList[position], clickListener)
    }
}
