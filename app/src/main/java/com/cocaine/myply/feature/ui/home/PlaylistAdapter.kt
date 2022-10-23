package com.cocaine.myply.feature.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.MusicData
import com.cocaine.myply.feature.ui.search.SearchTagAdapter

class PlaylistAdapter(private val onLikedClick: (Boolean, String) -> Unit) :
    ListAdapter<MusicData, PlaylistAdapter.PlaylistViewHolder>(diffUtil) {

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MusicData>() {
            override fun areItemsTheSame(oldItem: MusicData, newItem: MusicData): Boolean {
                return oldItem.youtubeVideoID == newItem.youtubeVideoID
            }

            override fun areContentsTheSame(
                oldItem: MusicData,
                newItem: MusicData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    class PlaylistViewHolder(
        private val binding: ItemPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val tagAdapter = SearchTagAdapter()

        fun bind(video: MusicData, onLikedClick: (Boolean, String) -> Unit) {
            binding.video = video

            binding.playlistTags.adapter = tagAdapter
            video.youtubeTags?.let {
                tagAdapter.submitList(it)
            }

            binding.playlistPlayBtn.setOnClickListener {
                kotlin.runCatching {
                    binding.root.context.startActivity(
                        Intent(Intent.ACTION_VIEW).setData(Uri.parse(video.videoDeepLink))
                            .setPackage("com.google.android.youtube")
                    )
                }
            }

            binding.playlistHeart.setOnClickListener {
                onLikedClick(video.isMemoed, video.youtubeVideoID)
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
            )
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(currentList[position], onLikedClick)
    }
}
