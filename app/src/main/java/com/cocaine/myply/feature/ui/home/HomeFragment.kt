package com.cocaine.myply.feature.ui.home

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentHomeBinding
import com.cocaine.myply.databinding.ToastPlaylistSaveBinding
import com.cocaine.myply.feature.data.model.MemoInfo
import com.cocaine.myply.feature.data.model.PlaylistOrder
import com.cocaine.myply.feature.ui.dialog.MyPlyTwoButtonDialog
import com.cocaine.myply.feature.ui.keep.KeepFragment.Companion.MEMO_KEY
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onPause() {
        super.onPause()
        homeViewModel.clearCreatedMemo()
    }

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
        homeViewModel.createdMemo.observe(viewLifecycleOwner) {
            if (it != null) {
                showToastPlaylistSaveView(it)
            }
        }
    }

    private fun onLikedClick(isMemoed: Boolean, youtubeVideoID: String) {
        when (isMemoed) {
            true -> showMemoDeleteDialog(youtubeVideoID)
            false -> homeViewModel.createMemo(youtubeVideoID)
        }
    }

    private fun showMemoDeleteDialog(youtubeVideoID: String) {
        val navController = findNavController()
        if (navController.currentDestination?.id == R.id.homeFragment) {
            MyPlyTwoButtonDialog.setDialogContent(requireContext().getString(R.string.keep_delete_title),
                requireContext().getString(R.string.keep_delete_body),
                requireContext().getString(R.string.keep_delete_pos),
                requireContext().getString(R.string.keep_delete_neg),
                {
                    homeViewModel.deleteMemo(youtubeVideoID)
                    navController.popBackStack()
                },
                { navController.popBackStack() })
            findNavController().navigate(R.id.myPlyTwoButtonDialog)
        } else {
            Toast.makeText(requireContext(), "delete memo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showToastPlaylistSaveView(memoInfo: MemoInfo) {
        DataBindingUtil.bind<ToastPlaylistSaveBinding>(
            LayoutInflater.from(requireContext()).inflate(
                R.layout.toast_playlist_save,
                null,
                false
            )
        )?.apply {
            this.btnMoveToKeepWrite.setOnClickListener {
                findNavController().let { controller ->
                    if (controller.currentDestination?.id == R.id.homeFragment) {
                        val bundle = bundleOf(MEMO_KEY to memoInfo)
                        controller.navigate(R.id.action_homeFragment_to_keepWriteFragment, bundle)
                    }
                }
            }
        }?.root?.let { toast ->
            binding?.root?.let {
                Snackbar.make(it, "", Snackbar.LENGTH_SHORT).apply {
                    (view as Snackbar.SnackbarLayout).addView(toast, 0)
                }.show()
            }
        }
    }
}
