package com.example.movieslookupmanual

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        // setting up the RV adapter and action on click
        val adapter = RVAdapter { item: GeneralRVType ->
            openDetails(item)
        }
        recycler.adapter = adapter
        adapter.setItems(items)

    }

    // moving to the DetailsFragment carrying the data with Safe Args
    fun openDetails(item: GeneralRVType) {
        findNavController().navigate(MainFragmentDirections.actionMainToDetails(item))
    }
}
