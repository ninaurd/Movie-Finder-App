package com.example.movieslookupmanual.data

import androidx.annotation.DrawableRes

data class FilmData(
    override val id: Int,
    val title: String,
    val year: Int,
    // Int: id of the image from res
    @DrawableRes val poster: Int,
    val desc: String
) : GeneralRVType