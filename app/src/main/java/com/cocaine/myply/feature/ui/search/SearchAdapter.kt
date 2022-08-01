package com.cocaine.myply.feature.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cocaine.myply.R
import com.cocaine.myply.databinding.ItemPlaylistBinding
import com.cocaine.myply.feature.data.model.SearchResponse

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    val searchResultPlayList = ArrayList<SearchResponse>()

    class SearchViewHolder(private val binding: ItemPlaylistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SearchResponse) {
            
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding = DataBindingUtil.inflate<ItemPlaylistBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_playlist,
            parent,
            false
        )
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(searchResultPlayList[position])
    }

    override fun getItemCount(): Int = searchResultPlayList.size
}