package com.cocaine.myply.feature.ui.keep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemKeepBinding
import com.cocaine.myply.feature.data.model.MemoResponse

class KeepAdapter : RecyclerView.Adapter<KeepAdapter.KeepViewHolder>() {
    val list = ArrayList<MemoResponse>()

    class KeepViewHolder(private val binding: ItemKeepBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(memo: MemoResponse) {
            binding.memo = memo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepAdapter.KeepViewHolder {
        val binding = DataBindingUtil.inflate<ItemKeepBinding>(LayoutInflater.from(parent.context), R.layout.item_keep, parent, false)
        return KeepViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KeepAdapter.KeepViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}