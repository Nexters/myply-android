package com.cocaine.myply.feature.ui.keep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemKeepBinding
import com.cocaine.myply.feature.data.model.MemoResponse
import com.google.android.material.chip.Chip

class KeepAdapter(private val moveToDetail:(Int) -> Unit) : ListAdapter<MemoResponse, KeepAdapter.KeepViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<MemoResponse>() {
            override fun areItemsTheSame(oldItem: MemoResponse, newItem: MemoResponse): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MemoResponse, newItem: MemoResponse): Boolean {
                return oldItem.memoID == newItem.memoID
            }
        }
    }

    class KeepViewHolder(private val binding: ItemKeepBinding, private val moveToDetail: (Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.keepEditBtn.setOnClickListener {
                moveToDetail(adapterPosition)
            }
        }

        fun bind(memo: MemoResponse) {
            binding.memo = memo

            for(i in memo.keywords) {
                Chip(binding.root.context).apply {
                    text = i
                    isCheckable = false
                    binding.keepTags.addView(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepViewHolder {
        val binding = DataBindingUtil.inflate<ItemKeepBinding>(LayoutInflater.from(parent.context), R.layout.item_keep, parent, false)
        return KeepViewHolder(binding, moveToDetail)
    }

    override fun onBindViewHolder(holder: KeepViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}