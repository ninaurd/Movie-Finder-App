package com.example.movieslookupmanual.data

import android.os.Parcelable

interface GeneralRVType : Parcelable {
    val id: Int
    val title: String
    val desc: String
    val poster: Int
    var isFav: Boolean
}