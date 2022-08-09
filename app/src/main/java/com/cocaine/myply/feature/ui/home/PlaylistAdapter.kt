package com.cocaine.myply.feature.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.VideoResponse

class PlaylistAdapter(private val onLikedClick: (String) -> Unit) :
    ListAdapter<VideoResponse, PlaylistAdapter.PlaylistViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<VideoResponse>() {
            override fun areItemsTheSame(oldItem: VideoResponse, newItem: VideoResponse): Boolean {
                return oldItem.youtubeVideoId == newItem.youtubeVideoId
            }

            override fun areContentsTheSame(
                oldItem: VideoResponse,
                newItem: VideoResponse
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding,
        private val onLikedClick: (String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: VideoResponse) {
            binding.video = video

            binding.playlistHeart.setOnClickListener {
                onLikedClick(video.youtubeVideoId)
            }
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
