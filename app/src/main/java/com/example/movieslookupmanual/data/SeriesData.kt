package com.example.movieslookupmanual.data

import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class SeriesData(
    override val id: Int,
    override val title: String,
    val years: String,
    val seasons: Int,
    // Int: id of the image from res
    @DrawableRes override val poster: Int,
    override val desc: String,
    override var isFav:Boolean = false
) : GeneralRVType