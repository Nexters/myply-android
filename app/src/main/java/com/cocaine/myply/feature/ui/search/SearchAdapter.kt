package com.cocaine.myply.feature.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.VideoResponse
import com.google.android.material.chip.Chip

class SearchAdapter : ListAdapter<VideoResponse, SearchAdapter.SearchViewHolder>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoResponse>() {
            override fun areItemsTheSame(oldItem: VideoResponse, newItem: VideoResponse): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: VideoResponse,
                newItem: VideoResponse
            ): Boolean {
                return oldItem.youtubeVideoId == newItem.youtubeVideoId
            }

        }
    }

    class SearchViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: VideoResponse) {
            binding.video = data
            data.youtubeTags?.let { tags ->
                for(i in tags) {
                    Chip(binding.root.context).apply {
                        text = i
                        isCheckable = false
                        binding.playlistTags.addView(this)
                    }.setOnCheckedChangeListener { _, b ->
                        if(b) {

                        }
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