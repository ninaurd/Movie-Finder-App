package com.example.movieslookupmanual.adapters

import com.example.movieslookupmanual.data.GeneralRVType
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class RVAdapter() : ListDelegationAdapter<List<GeneralRVType>>() {
    init {
        delegatesManager.addDelegate(MoviesAdapterDelegate())
        delegatesManager.addDelegate(SeriesAdapterDelegate())
    }

    override fun setItems(items: List<GeneralRVType>?) {
        super.setItems(items)
        notifyDataSetChanged()
    }
}