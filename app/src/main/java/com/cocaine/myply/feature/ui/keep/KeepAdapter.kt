package com.cocaine.myply.feature.ui.keep

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.core.extension.setBackgroundTint
import com.cocaine.myply.databinding.ItemKeepBinding
import com.cocaine.myply.feature.data.model.MemoInfo
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.resources.TextAppearance

class KeepAdapter(private val moveToDetail: (Int) -> Unit, private val deleteMemo: (Int) -> Unit) :
    ListAdapter<MemoInfo, KeepAdapter.KeepViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MemoInfo>() {
            override fun areItemsTheSame(oldItem: MemoInfo, newItem: MemoInfo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MemoInfo, newItem: MemoInfo): Boolean {
                return oldItem.memoID == newItem.memoID
            }
        }
    }

    class KeepViewHolder(
        private val binding: ItemKeepBinding,
        private val moveToDetail: (Int) -> Unit,
        private val deleteMemo: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.keepEditBtn.setOnClickListener {
                moveToDetail(adapterPosition)
            }

            binding.keepLike.setOnClickListener {
                deleteMemo(adapterPosition)
            }
        }

        fun bind(memo: MemoInfo) {
            binding.memo = memo

            val chipDrawable = ChipDrawable.createFromAttributes(
                binding.root.context,
                null,
                0,
                R.style.MyPly_Chip_Keep
            )
            memo.keywords?.forEach { keyword ->
                Chip(binding.root.context).apply {
                    text = keyword
                    setTextColor(Color.WHITE)
                    setTextAppearance(R.style.Body2_Bold)
                    setChipDrawable(chipDrawable)

                    isCheckable = false
                    isClickable = false

                    binding.keepTags.addView(this)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepViewHolder {
        val binding = DataBindingUtil.inflate<ItemKeepBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_keep,
            parent,
            false
        )
        return KeepViewHolder(binding, moveToDetail, deleteMemo)
    }

    override fun onBindViewHolder(holder: KeepViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}