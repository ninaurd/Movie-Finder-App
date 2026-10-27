package com.example.movieslookupmanual.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.movieslookupmanual.GeneralTypeDiffUtil
import com.example.movieslookupmanual.data.GeneralRVType
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RVAdapter(
    onItemClick: (GeneralRVType) -> Unit
) : ListDelegationAdapter<List<GeneralRVType>>() {
    init {
        delegatesManager.addDelegate(MoviesAdapterDelegate(onItemClick))
        delegatesManager.addDelegate(SeriesAdapterDelegate(onItemClick))
    }

    override fun setItems(newItems: List<GeneralRVType>?) {
        val oldList = items ?: emptyList()
        val newList = newItems ?: emptyList()
        val diffResult = DiffUtil.calculateDiff(GeneralTypeDiffUtil(oldList, newList))
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }
}