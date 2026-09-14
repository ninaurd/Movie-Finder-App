package com.example.movieslookupmanual

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslookupmanual.adapters.RVAdapter
import com.example.movieslookupmanual.data.FilmData
import com.example.movieslookupmanual.data.GeneralRVType
import com.example.movieslookupmanual.data.SeriesData
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val regularToolbar = findViewById<MaterialToolbar>(R.id.top_toolbar)
        regularToolbar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.navigation_toast, Toast.LENGTH_SHORT).show()
        }

        regularToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu_menu -> {
                    Toast.makeText(this, R.string.menu_toast, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.menu_settings -> {
                    Toast.makeText(this, R.string.settings_toast, Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        val bottomToolbar = findViewById<BottomNavigationView>(R.id.bottom_toolbar)
        bottomToolbar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_selections -> {
                    Toast.makeText(this, R.string.selections_toast, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.menu_favourites -> {
                    Toast.makeText(this, R.string.favourites_toast, Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.menu_for_later -> {
                    Toast.makeText(this, R.string.watch_later_toast, Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }

        val recycler = findViewById<RecyclerView>(R.id.recycler)
        val adapter = RVAdapter()
        recycler.adapter = adapter

        val items: List<GeneralRVType> = listOf(
            FilmData(1, "TITLE", 2000, R.drawable.me, "something about the film here something about the film here something about the film here something about the film here something about the film here something about the film here something about the film here"),
            FilmData(2, "TITLE", 2000, R.drawable.leon_1994, "something about the film here"),
            FilmData(3, "TITLE", 2000, R.drawable.joker_2019, "something about the film here"),
            SeriesData(4, "TITLE", 2000, 12, R.drawable.closer_2004, "something about the film here"),
            SeriesData(5, "TITLE", 2000, 5, R.drawable.seven_pounds_2008, "something about the film here"),
            FilmData(6, "TITLE", 2000, R.drawable.shutter_island_2010, "something about the film here"),
            FilmData(7, "TITLE", 2000, R.drawable.sixth_sense_1999, "something about the film here"),
            SeriesData(8, "TITLE", 2000, 3, R.drawable.the_green_mile_1999, "something about the film here"),
            FilmData(9, "TITLE", 2000, R.drawable.totoro_1988, "something about the film here"),
            FilmData(10, "TITLE", 2000, R.drawable.walle_2008, "something about the film here")
        )

        adapter.setItems(items)

        // NOT requested by the task: little toasts for the top scrolling bar
        val topScrollMovies = mapOf(
            R.id.walle to R.string.walle_toast,
            R.id.closer to R.string.closer_toast,
            R.id.joker to R.string.joker_toast,
            R.id.seven_pounds to R.string.seven_pounds_toast,
            R.id.shutter_island to R.string.shutter_island_toast,
            R.id.sixth_sense to R.string.sixth_sense_toast,
            R.id.leon to R.string.leon_toast,
            R.id.totoro to R.string.totoro_toast,
            R.id.the_green_mile to R.string.green_mile_toast
        )
        topScrollMovies.forEach { (imageId, textId) ->
            findViewById<ImageView>(imageId).setOnClickListener {
                Toast.makeText(this, textId, Toast.LENGTH_SHORT).show()
            }
        }
    }
}