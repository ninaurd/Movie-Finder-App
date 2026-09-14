package com.example.movieslookupmanual.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslookupmanual.R
import com.example.movieslookupmanual.data.FilmData
import com.example.movieslookupmanual.data.GeneralRVType
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate

class MoviesAdapterDelegate :
    AbsListItemAdapterDelegate<FilmData, GeneralRVType, MoviesAdapterDelegate.MyVH>() {
    override fun isForViewType(item: GeneralRVType, list: List<GeneralRVType?>, pos: Int): Boolean {
        return item is FilmData
    }

    override fun onCreateViewHolder(parent: ViewGroup): MyVH {
        return MyVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_item_film_data, parent, false)
        )
    }

    override fun onBindViewHolder(myClass: FilmData, vh: MyVH, payloads: List<Any?>) {
        vh.posterView.setImageResource(myClass.poster)
        vh.titleView.text = myClass.title
        vh.yearView.text = myClass.year.toString()
        vh.descView.text = myClass.desc
    }

    inner class MyVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val posterView: ImageView = itemView.findViewById(R.id.movies_icon)
        val titleView: TextView = itemView.findViewById(R.id.movies_title_text)
        val yearView: TextView = itemView.findViewById(R.id.years_text)
        val descView: TextView = itemView.findViewById(R.id.desc_text)
    }
}