package com.cocaine.myply.feature.ui.home

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentHomeBinding
import com.cocaine.myply.databinding.ToastPlaylistDeleteBinding
import com.cocaine.myply.databinding.ToastPlaylistSaveBinding
import com.cocaine.myply.feature.data.model.MemoState
import com.cocaine.myply.feature.data.model.PlaylistOrder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var playlistAdapter: PlaylistAdapter

    override fun setup() {
        setPlaylistAdapter()
        setPlaylistChipGroupClickListener()

        setViewModelObserver()
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

    private fun setViewModelObserver() {
        homeViewModel.playlistOrder.observe(viewLifecycleOwner) {
            homeViewModel.loadPlaylists()
        }
        homeViewModel.playlists.observe(viewLifecycleOwner) {
            playlistAdapter.submitList(it)
        }
        homeViewModel.likedUpdatePlaylistId.observe(viewLifecycleOwner) { (playlistId, isLiked) ->
            showPlaylistLikedToast(playlistId, isLiked)
        }
    }

    private fun onLikedClick(clickedPosition: Int) {
        homeViewModel.updatePlaylistLiked(clickedPosition)
    }

    private fun showPlaylistLikedToast(playlistId: String, isLiked: MemoState) {
        when (isLiked) {
            MemoState.LIKED, MemoState.FILLED -> getToastPlaylistSaveView(playlistId)
            else -> getToastPlaylistDeleteView()
        }?.let { toast ->
            binding?.root?.let {
                Snackbar.make(it, "", Snackbar.LENGTH_SHORT).apply {
                    (view as Snackbar.SnackbarLayout).addView(toast, 0)
                }.show()
            }
        }
    }

    private fun getToastPlaylistSaveView(playlistId: String): View? {
        return DataBindingUtil.bind<ToastPlaylistSaveBinding>(
            LayoutInflater.from(requireContext()).inflate(
                R.layout.toast_playlist_save,
                null,
                false
            )
        )?.apply {
            this.btnMoveToKeepWrite.setOnClickListener {
                // move to write
                findNavController().let { controller ->
                    if (controller.currentDestination?.id == R.id.homeFragment) {
                        controller.navigate(R.id.action_homeFragment_to_keepWriteFragment)
                    }
                }
            }
        }?.root
    }

    private fun getToastPlaylistDeleteView(): View? {
        return DataBindingUtil.bind<ToastPlaylistDeleteBinding>(
            LayoutInflater.from(requireContext()).inflate(
                R.layout.toast_playlist_delete,
                null,
                false
            )
        )?.root
    }
}
