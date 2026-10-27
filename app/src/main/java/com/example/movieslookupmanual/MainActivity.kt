package com.example.movieslookupmanual

import android.content.Intent
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
        val adapter = RVAdapter(::openDetails)
        recycler.adapter = adapter

        // temporary fixed movies and series list - will be replaced with some server import
        val items: List<GeneralRVType> = listOf(
            FilmData(
                1,
                getString(R.string.fight_club_title),
                1999,
                R.drawable.fight_club,
                getString(R.string.fight_club)
            ),
            FilmData(
                2,
                getString(R.string.matrix_title),
                1999,
                R.drawable.matrix,
                getString(R.string.matrix)
            ),
            FilmData(
                3,
                getString(R.string.the_housemaid_title),
                2025,
                R.drawable.the_housemaid,
                getString(R.string.the_housemaid)
            ),
            SeriesData(
                4,
                getString(R.string.money_heist_title),
                "(2017-2021)",
                5,
                R.drawable.money_heist,
                getString(R.string.money_heist)
            ),
            SeriesData(
                5,
                getString(R.string.squid_game_title),
                "(2021-2025)",
                3,
                R.drawable.squid_game,
                getString(R.string.squid_game)
            ),
            FilmData(
                6,
                getString(R.string.the_substance_title),
                2024,
                R.drawable.the_substance,
                getString(R.string.the_substance)
            ),
            FilmData(
                7,
                getString(R.string.untouchable_title),
                2011,
                R.drawable.untouchable,
                getString(R.string.untouchable)
            ),
            SeriesData(
                8,
                getString(R.string.blacklist_title),
                "(2013-2023)",
                10,
                R.drawable.blacklist,
                getString(R.string.blacklist)
            ),
            FilmData(9, getString(R.string.up_title), 2009, R.drawable.up, getString(R.string.up)),
            FilmData(
                10,
                getString(R.string.wicker_park_title),
                2004,
                R.drawable.wicker_park,
                getString(R.string.wicker_park)
            )
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

    // separate function for transferring data bit by bit to the activity
    private fun openDetails(item: GeneralRVType) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("desc", item.desc)
        intent.putExtra("title", item.title)
        intent.putExtra("poster", item.poster)
        startActivity(intent)
    }
}