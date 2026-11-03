package com.example.movieslookupmanual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslookupmanual.adapters.RVAdapter
import com.example.movieslookupmanual.data.FilmData
import com.example.movieslookupmanual.data.GeneralRVType
import com.example.movieslookupmanual.data.SeriesData

class MainFragment : Fragment() {

    // temporary fixed movies and series list - will be replaced with some server import
    val items: List<GeneralRVType> by lazy {
        listOf(
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
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler)

        // setting up the RV adapter
        val adapter = RVAdapter { item: GeneralRVType ->
            openDetails(item)
        }
        recycler.adapter = adapter
        adapter.setItems(items)

        // NOT requested by the task: little toasts for the top scrolling bar in one Map
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
        /* iterating over the Map, finding each element by id, setting a click listener
        and assigning a toast with the corresponding string from the Map*/
        topScrollMovies.forEach { (imageId, textId) ->
            view.findViewById<ImageView>(imageId).setOnClickListener {
                Toast.makeText(requireContext(), textId, Toast.LENGTH_SHORT).show()
            }
        }
    }

    // calling the function from MainActivity, passing on the item
    private fun openDetails(item: GeneralRVType) {
        (requireActivity() as MainActivity).launchDetailsFragment(item)
    }
}