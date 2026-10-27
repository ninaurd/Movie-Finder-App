package com.example.movieslookupmanual

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

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
        val detailsTopBar = findViewById<Toolbar>(R.id.details_toolbar)
        detailsTopBar.title = title
        // getting and showing 'poster'
        val poster = intent.getIntExtra("poster", 0)
        val posterView = findViewById<AppCompatImageView>(R.id.details_poster)
        posterView.setImageResource(poster)

        val collapsingToolbar = findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbar.apply {
            setExpandedTitleColor(
                ContextCompat.getColor(
                    this@DetailsActivity,
                    R.color.white_day_night
                )
            )
            setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    this@DetailsActivity,
                    R.color.white_day_night
                )
            )
        }

        val detailsFAB = findViewById<FloatingActionButton>(R.id.details_fab)
        detailsFAB.setOnClickListener {
            Toast.makeText(
                this, R.string.share, Toast.LENGTH_SHORT
            ).show()
        }

        // on click snackbars
        detailsTopBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.details_favourites -> setSnackBar(
                    detailsTopBar,
                    getString(R.string.added_to_fav)
                ).show()

                R.id.details_for_later -> setSnackBar(
                    detailsTopBar,
                    getString(R.string.saved_for_later)
                ).show()

                R.id.details_selections -> setSnackBar(
                    detailsTopBar,
                    getString(R.string.saved_for_later)
                ).show()
            }
            false
        }

        // on click bottom sheet
        val bottomImage = findViewById<ImageView>(R.id.rate_us_img)
        bottomImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, getString(R.string.link_to_google_play).toUri())
            startActivity(intent)
        }
    }

    /* temporary method that prepares the snackbar and sets a default 'Cancel' toast - most probably
    will have to change this when actual actions on click will be implemented */
    fun setSnackBar(parent: View, message: String): Snackbar {
        val snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT)
        snackbar.apply {
            // default toast on click
            setAction(R.string.undo) {
                Toast.makeText(
                    this@DetailsActivity, R.string.undone, Toast.LENGTH_SHORT
                ).show()
            }
            // white button color
            setActionTextColor(
                ContextCompat.getColor(
                    this@DetailsActivity,
                    R.color.white_day_night
                )
            )
        }
        return snackbar
    }
}