package com.example.movieslookupmanual

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieslookupmanual.adapters.RVAdapter
import com.example.movieslookupmanual.data.GeneralRVType

class FavFragment : Fragment() {

    private lateinit var myAdapter: RVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // preparing an empty list for the favourites
        val favList: List<GeneralRVType> = emptyList()

        val favRecycler = view.findViewById<RecyclerView>(R.id.fav_recycler)
        // setting up the adapter
        favRecycler.apply {
            myAdapter =
                RVAdapter { item: GeneralRVType ->
                    findNavController().navigate(FavFragmentDirections.actionFavToDetails(item))
                }
            adapter = myAdapter
        }
        //Кладем нашу БД в RV
        myAdapter.setItems(favList)
    }

}

