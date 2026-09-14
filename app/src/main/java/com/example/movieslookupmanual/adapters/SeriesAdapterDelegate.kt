package com.example.movieslookupmanual.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslookupmanual.R
import com.example.movieslookupmanual.data.GeneralRVType
import com.example.movieslookupmanual.data.SeriesData
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class SeriesAdapterDelegate :
    AbsListItemAdapterDelegate<SeriesData, GeneralRVType, SeriesAdapterDelegate.MyVH>() {
    override fun isForViewType(item: GeneralRVType, list: List<GeneralRVType?>, pos: Int): Boolean {
        return item is SeriesData
    }

    override fun onCreateViewHolder(parent: ViewGroup): MyVH {
        return MyVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_series_data, parent, false)
        )
    }

    override fun onBindViewHolder(myClass: SeriesData, vh: MyVH, payloads: List<Any?>) {
        vh.posterView.setImageResource(myClass.poster)
        vh.titleView.text = myClass.title
        vh.yearView.text = myClass.years.toString()
        vh.seasonsView.text = "Seasons: " + myClass.seasons.toString()
        vh.descView.text = myClass.desc
    }

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.series_icon)
        val titleView: TextView = itemView.findViewById(R.id.series_title_text)
        val seasonsView: TextView = itemView.findViewById(R.id.seasons_text)
        val yearView: TextView = itemView.findViewById(R.id.years_text)
        val descView: TextView = itemView.findViewById(R.id.desc_text)
    }
}