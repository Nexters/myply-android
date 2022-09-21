package com.cocaine.myply.feature.ui.search

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.ChipStyles
import com.cocaine.myply.feature.data.model.MemoState
import com.cocaine.myply.feature.data.model.MusicResponse
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable

class SearchAdapter(private val getYoutubeId: (Int, String) -> Unit) : ListAdapter<MusicResponse, SearchAdapter.SearchViewHolder>(diffUtil) {
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

    class SearchViewHolder(private val binding: ItemPlaylistBinding, private val getYoutubeId: (Int, String) -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MusicResponse) {
            binding.video = data
            val chipStyles = ChipStyles.values()
            data.youtubeTags?.forEachIndexed { i, tag ->
                val chipDrawable = ChipDrawable.createFromAttributes(
                    binding.root.context,
                    null,
                    0,
                    chipStyles[i % chipStyles.size].id
                )
                Chip(binding.root.context).apply {
                    text = tag
                    isCheckable = false
                    isClickable = false

                    setTextColor(Color.WHITE)
                    setTextAppearance(R.style.Body2_Semibold)

                    setChipDrawable(chipDrawable)

                    binding.playlistTags.addView(this)
                }
            }

            binding.playlistHeart.setOnClickListener {
                binding.video = data.copy(memoState = MemoState.LIKED)
                android.util.Log.e("video", "${binding.video.toString()}")
                getYoutubeId(adapterPosition, data.youtubeVideoID)
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
        return SearchViewHolder(binding, getYoutubeId)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}