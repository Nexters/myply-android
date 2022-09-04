package com.cocaine.myply.feature.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.MusicResponse

class PlaylistAdapter(private val onLikedClick: (Int) -> Unit) :
    ListAdapter<MusicResponse, PlaylistAdapter.PlaylistViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicResponse>() {
            override fun areItemsTheSame(oldItem: MusicResponse, newItem: MusicResponse): Boolean {
                return oldItem.youtubeVideoID == newItem.youtubeVideoID
            }

            override fun areContentsTheSame(
                oldItem: MusicResponse,
                newItem: MusicResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding,
        private val onLikedClick: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.playlistHeart.setOnClickListener {
                onLikedClick(adapterPosition)
            }
        }

        fun bind(video: MusicResponse) {
            binding.video = video
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlaylistViewHolder {
        return PlaylistViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_playlist,
                parent,
                false
            ), onLikedClick
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}
