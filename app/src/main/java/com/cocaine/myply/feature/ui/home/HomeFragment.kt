package com.cocaine.myply.feature.ui.home

import androidx.fragment.app.viewModels
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentHomeBinding
import com.cocaine.myply.feature.data.model.PlaylistOrder

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var playlistAdapter: PlaylistAdapter

    override fun setup() {
        setPlaylistAdapter()
        setPlaylistChipGroupClickListener()

        setViewModel()
    }

    private fun setPlaylistAdapter() {
        playlistAdapter = PlaylistAdapter(::onLikedClick)
        binding?.homePlaylist?.adapter = playlistAdapter
    }

    private fun setPlaylistChipGroupClickListener() {
        val playlistOrderChipGroupMap = mapOf(
            binding?.homeRecent?.id to PlaylistOrder.RECENT,
            binding?.homePopular?.id to PlaylistOrder.POPULAR,
            binding?.homePreference?.id to PlaylistOrder.PREFERENCE
        )

        binding?.homePlaylistOrder?.setOnCheckedChangeListener { _, checkedId ->
            playlistOrderChipGroupMap[checkedId]?.let { homeViewModel.updatePlaylistOrder(it) }
        }
    }

    private fun setViewModel() {
        homeViewModel.playlists.observe(viewLifecycleOwner) {
            playlistAdapter.submitList(it)
        }
    }

    private fun onLikedClick(videoId: String) {
        homeViewModel.updatePlaylistLiked(videoId)
    }
}
