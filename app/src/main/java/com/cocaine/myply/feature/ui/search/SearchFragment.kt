package com.cocaine.myply.feature.ui.search

import android.content.Context
import android.graphics.Color
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.core.base.BaseFragment
import com.cocaine.myply.databinding.FragmentSearchBinding
import com.cocaine.myply.feature.data.model.MusicResponse
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var adapter: SearchAdapter

    override fun setup() {
        binding?.view = this
        binding?.viewmodel = viewModel

        setRecyclerview()
        setSearchButtonVisible()
        setViewModel()
        handleError()
        searchPlayListWithKeyboardEnter()
    }

    private fun setRecyclerview() {
        adapter = SearchAdapter{ adapterPosition, youtubeId ->
            viewModel.addMemo(youtubeId)
            adapter.notifyItemChanged(adapterPosition)
        }
        binding?.searchResultList?.adapter = adapter

        binding?.searchResultList?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                val loadPosition = recyclerView.adapter?.itemCount!! - 2

                if (viewModel.nextPageToken.value != null && lastItemPosition == loadPosition) {
                    binding?.searchEditTxt?.text?.let { query -> viewModel.searchMusicPlayList(query.toString()) }
                }
            }
        })
    }

    private fun setSearchButtonVisible() {
        binding?.searchEditTxt?.setOnFocusChangeListener { view, isFocus ->
            if (isFocus) {
                binding?.searchBtn?.visibility = View.VISIBLE
            } else {
                binding?.searchBtn?.visibility = View.INVISIBLE
            }
        }
    }

    private fun setViewModel() {
        viewModel.recommendKeyword.observe(this) { recommend ->
            val size = recommend.size - 1
            val randomNumbers = List(6) { Random.nextInt(0, size) }

            val chipStyles = listOf(
                R.style.MyPly_Chip_Red, R.style.MyPly_Chip_green, R.style.MyPly_Chip_yellow, R.style.MyPly_Chip_Blue
            )

            for (i in randomNumbers) {
                val chipDrawable = ChipDrawable.createFromAttributes(requireContext(), null, 0, chipStyles.random())
                Chip(requireContext()).apply {
                    text = recommend[i]
                    setTextColor(Color.WHITE)
                    setTextAppearance(R.style.Body2_Bold)

                    setChipDrawable(chipDrawable)

                    setOnClickListener { view ->
                        binding?.searchEditTxt?.setText(recommend[i])
                        searchPlayList(recommend[i])
                    }
                    binding?.searchRecomend?.addView(this)
                }
            }
        }

        viewModel.searchMusicResponse.observe(this) { result ->
            val curList = mutableListOf<MusicResponse>().apply {
                addAll(adapter.currentList)
                if (result != null) addAll(result)
            }

            adapter.submitList(curList)
            setSearchViewVisibility(result?.size ?: 0)
        }
    }

    private fun setSearchViewVisibility(resultSize: Int) {
        binding?.searchShimmer?.visibility = View.INVISIBLE

        if (resultSize > 0) {
            binding?.searchResultList?.visibility = View.VISIBLE
            binding?.searchNoResultImg?.visibility = View.INVISIBLE
            binding?.searchNoResultTxt?.visibility = View.INVISIBLE
        } else {
            binding?.searchResultList?.visibility = View.INVISIBLE
            binding?.searchNoResultImg?.visibility = View.VISIBLE

            binding?.searchNoResultTxt?.setText(
                getString(
                    R.string.search_no_result,
                    binding?.searchEditTxt?.text ?: ""
                )
            )
            binding?.searchNoResultTxt?.visibility = View.VISIBLE
        }
    }

    private fun handleError() {
        viewModel.errMsg.observe(this) {
            if (!it.hasBeenHandled) {
                binding?.searchShimmer?.visibility = View.INVISIBLE
                Toast.makeText(requireContext(), it.peekContent(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun searchPlayList(query: String?) {
        binding?.searchSubtitle?.visibility = View.GONE
        binding?.searchRecomend?.visibility = View.GONE

        binding?.searchEditTxt?.clearFocus()
        adapter.submitList(null)

        binding?.searchShimmer?.visibility = View.VISIBLE
        viewModel.searchMusicPlayList(query)
        hideSoftKeyboard()
    }

    private fun searchPlayListWithKeyboardEnter() {
        binding?.searchEditTxt?.setOnKeyListener { view, i, keyEvent ->
            when (keyEvent.keyCode) {
                KeyEvent.KEYCODE_ENTER -> {
                    searchPlayList(viewModel.curSearchMsg.value)
                }

                KeyEvent.KEYCODE_BACK -> {
                    binding?.searchEditTxt?.text?.clear()
                }
            }

            return@setOnKeyListener true
        }
    }

    private fun hideSoftKeyboard() {
        val view = requireView()
        val imm =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}