package com.cocaine.myply.feature.ui.keep

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentKeepBinding
import com.cocaine.myply.feature.ui.dialog.MyPlyTwoButtonDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KeepFragment : BaseFragment<FragmentKeepBinding>(R.layout.fragment_keep) {
    private val viewModel: KeepViewModel by viewModels()
    private lateinit var adapter: KeepAdapter

    companion object {
        const val MEMO_KEY = "MEMO_DATA"
    }

    override fun onResume() {
        super.onResume()

        viewModel.getKeepUserMemoList()
    }

    override fun setup() {
        binding?.viewmodel = viewModel

        setRecyclerView()
        setViewModel()
    }

    private fun setRecyclerView() {
        adapter = KeepAdapter({ position ->
            val navController = findNavController()
            if (navController.currentDestination?.id == R.id.keepFragment) {
                val bundle = bundleOf(MEMO_KEY to adapter.currentList[position])
                navController.navigate(R.id.action_keepFragment_to_keepDetailFragment, bundle)
            }
        }, { position ->
            val data = adapter.currentList[position]
            android.util.Log.e("delete data", "$data")

            val navController = findNavController()
            if (data.body.length > 0 && navController.currentDestination?.id == R.id.keepFragment) {
                MyPlyTwoButtonDialog.setDialogContent(requireContext().getString(R.string.keep_delete_title),
                    requireContext().getString(R.string.keep_delete_body),
                    requireContext().getString(R.string.keep_delete_pos),
                    requireContext().getString(R.string.keep_delete_neg), {

                    }, { navController.popBackStack() })
                findNavController().navigate(R.id.myPlyTwoButtonDialog)
            } else {
                Toast.makeText(requireContext(), "delete memo", Toast.LENGTH_SHORT).show()
            }

        })

        binding?.keepMemoList?.adapter = adapter
    }

    private fun setViewModel() {
        viewModel.userMemoList.observe(this) {
            adapter.submitList(it)
        }
    }
}