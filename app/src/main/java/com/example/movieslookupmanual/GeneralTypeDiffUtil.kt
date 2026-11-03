package com.example.movieslookupmanual

import androidx.recyclerview.widget.DiffUtil
import com.example.movieslookupmanual.data.GeneralRVType

class GeneralTypeDiffUtil(
    val oldList: List<GeneralRVType>, val newList: List<GeneralRVType>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        val old = oldList[oldItemPos]
        val new = newList[newItemPos]
        return old.id == new.id && old::class == new::class
    }

    override fun areContentsTheSame(oldItemPos: Int, newItemPos: Int): Boolean {
        return oldList[oldItemPos] == newList[newItemPos]
    }
}

