package com.cocaine.myply.feature.ui.keep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemFrameColorBinding
import com.cocaine.myply.feature.data.model.ShareColorItem
import okhttp3.internal.notify

class KeepShareAdapter(private val getSelectedViewColor: () -> ShareColorItem, private val updateSelectedViewColor: (ShareColorItem) -> Unit) :
    ListAdapter<ShareColorItem, KeepShareAdapter.KeepShareItemViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<ShareColorItem>() {
            override fun areItemsTheSame(
                oldItem: ShareColorItem,
                newItem: ShareColorItem
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: ShareColorItem,
                newItem: ShareColorItem
            ): Boolean {
                return oldItem.viewColor == newItem.viewColor && oldItem.fontColor == newItem.fontColor
            }

        }
    }

    class KeepShareItemViewHolder(
        private val binding: ItemFrameColorBinding,
        private val getSelectedViewColor: () -> ShareColorItem,
        private val updateSelectedViewColor: (ShareColorItem) -> Unit,
        private val onClick: (item: ShareColorItem) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.rootView.setOnClickListener {
                val prevItem = getSelectedViewColor()

                val selectedItem = ShareColorItem.values()[adapterPosition]
                updateSelectedViewColor(selectedItem)

                onClick(prevItem)
                onClick(selectedItem)
            }
        }

        fun bind(item: ShareColorItem) {
            binding.color = item
            binding.isSelected = (getSelectedViewColor().viewColor == item.viewColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepShareItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemFrameColorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_frame_color,
            parent,
            false
        )
        return KeepShareItemViewHolder(binding, getSelectedViewColor,updateSelectedViewColor) { item ->
            ShareColorItem.values().forEachIndexed { index, shareColorItem ->
                if(item == shareColorItem) {
                    notifyItemChanged(index)
                }
            }
        }
    }

    override fun onBindViewHolder(holder: KeepShareItemViewHolder, position: Int) {
        holder.bind(ShareColorItem.values()[position])
    }

    override fun getItemCount(): Int = ShareColorItem.values().size
}