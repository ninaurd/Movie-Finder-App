package com.example.movieslookupmanual.data

import androidx.annotation.DrawableRes

data class SeriesData(
    override val id: Int,
    val title: String,
    val years: Int,
    val seasons: Int,
    // Int: id of the image from res
    @DrawableRes val poster: Int,
    val desc: String
) : GeneralRVType