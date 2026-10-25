package com.example.movieslookupmanual

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        // getting and showing 'desc'
        val desc = intent.getStringExtra("desc")
        val descView = findViewById<TextView>(R.id.details_description)
        descView.text = desc
        // getting and showing 'title'
        val title = intent.getStringExtra("title")
        val titleView = findViewById<Toolbar>(R.id.details_toolbar)
        titleView.title = title
        // getting and showing 'poster'
        val poster = intent.getIntExtra("poster", 0)
        val posterView = findViewById<AppCompatImageView>(R.id.details_poster)
        posterView.setImageResource(poster)
    }
}