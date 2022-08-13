package com.cocaine.myply.feature.ui.home

import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentHomeBinding
import com.cocaine.myply.feature.data.model.PlaylistOrder

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var playlistAdapter: PlaylistAdapter

    override fun setup() {
        setPlaylistAdapter()
        setPlaylistScrollListener()
        setPlaylistChipGroupClickListener()

        setViewModel()
    }

    private fun setPlaylistAdapter() {
        playlistAdapter = PlaylistAdapter(::onLikedClick)
        binding?.homePlaylist?.adapter = playlistAdapter
    }

    private fun setPlaylistScrollListener() {
        binding?.homePlaylist?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                // TODO :: 도연 :: 스크롤 속도가 느릴 때 방향 인식이 이상해짐
                binding?.homePlaylistOrder?.visibility = when {
                    dy > 0 -> View.GONE
                    dy < 0 -> View.VISIBLE
                    else -> return
                }
            }
        })
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

    private fun onLikedClick(clickedPosition: Int) {
        homeViewModel.updatePlaylistLiked(clickedPosition)
    }
}
