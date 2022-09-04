package com.cocaine.myply.feature.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.MusicResponse
import com.google.android.material.chip.Chip

class SearchAdapter : ListAdapter<MusicResponse, SearchAdapter.SearchViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicResponse>() {
            override fun areItemsTheSame(oldItem: MusicResponse, newItem: MusicResponse): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: MusicResponse,
                newItem: MusicResponse
            ): Boolean {
                return oldItem.youtubeVideoID == newItem.youtubeVideoID
            }

        }
    }

    class SearchViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MusicResponse) {
            binding.video = data
            data.youtubeTags?.forEach { tag ->
                Chip(binding.root.context).apply {
                    text = tag
                    isCheckable = false
                    binding.playlistTags.addView(this)
                }.setOnCheckedChangeListener { _, b ->
                    if (b) {

                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = DataBindingUtil.inflate<ItemPlaylistBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_playlist,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}