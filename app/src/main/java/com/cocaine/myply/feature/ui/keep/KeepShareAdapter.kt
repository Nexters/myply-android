package com.cocaine.myply.feature.ui.keep

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemFrameColorBinding
import com.cocaine.myply.feature.data.model.ShareColorItem

class KeepShareAdapter(private val viewModel: KeepShareViewModel) : RecyclerView.Adapter<KeepShareAdapter.KeepShareItemViewHolder>() {
    private val itemList = mutableListOf<ShareColorItem>(
        ShareColorItem(R.color.white, R.color.black),
        ShareColorItem(R.color.primary_green_basic, R.color.white),
        ShareColorItem(R.color.primary_green_light, R.color.white),
        ShareColorItem(R.color.secondary_red, R.color.white),
        ShareColorItem(R.color.secondary_brown, R.color.white),
        ShareColorItem(R.color.secondary_butter, R.color.black),
        ShareColorItem(R.color.secondary_blue, R.color.black)
    )

    private var selectedItemPosition = 0

    class KeepShareItemViewHolder(private val binding: ItemFrameColorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShareColorItem, selectedItem: ShareColorItem) {
            binding.color = item
            binding.isSelected = (selectedItem.viewColor == item.viewColor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepShareItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemFrameColorBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_frame_color,
            parent,
            false
        )
        return KeepShareItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeepShareItemViewHolder, position: Int) {
        holder.bind(itemList[position], itemList[selectedItemPosition])
        holder.itemView.rootView.setOnClickListener {
            var prevPosition = selectedItemPosition
            selectedItemPosition = holder.adapterPosition

            viewModel.updateSelectedViewFrameColor(itemList[selectedItemPosition])

            notifyItemChanged(prevPosition)
            notifyItemChanged(selectedItemPosition)
        }
    }

    override fun getItemCount(): Int = itemList.size
}