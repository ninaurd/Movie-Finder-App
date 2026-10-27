package com.example.movieslookupmanual.data

import androidx.annotation.DrawableRes

data class FilmData(
    override val id: Int,
    override val title: String,
    val year: Int,
    // Int: id of the image from res
    @DrawableRes override val poster: Int,
    override val desc: String
) : GeneralRVType