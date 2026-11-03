package com.example.movieslookupmanual

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // all arguments are required to display the item
        val args = requireArguments()

        // getting data from Bundle by keys
        val desc = args.getString("desc")
        val title = args.getString("title")
        val poster = args.getInt("poster")

        // finding the elements in the layout
        val descView = view.findViewById<TextView>(R.id.details_description)
        val detailsTopBar = view.findViewById<Toolbar>(R.id.details_toolbar)
        val posterView = view.findViewById<AppCompatImageView>(R.id.details_poster)

        // displaying data in the Views
        descView.text = desc
        detailsTopBar.title = title
        posterView.setImageResource(poster)

        // setting toolbar's title color in expanded and collapsed states
        val collapsingToolbar = view.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)
        collapsingToolbar.apply {
            setExpandedTitleColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white_day_night
                )
            )
            setCollapsedTitleTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white_day_night
                )
            )
        }

        // just a little toast for the FAB
        val detailsFAB = view.findViewById<FloatingActionButton>(R.id.details_fab)
        detailsFAB.setOnClickListener {
            Toast.makeText(
                requireContext(), R.string.share, Toast.LENGTH_SHORT
            ).show()
        }

        // on click snackbars created via my custom method setSnackBar
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
            true
        }

        // on click bottom sheet with a link to Google Play
        val bottomImage = view.findViewById<ImageView>(R.id.rate_us_img)
        bottomImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, getString(R.string.link_to_google_play).toUri())
            startActivity(intent)
        }
    }

    /* temporary method that prepares the snackbar and sets a default 'Cancel' toast - most probably
    will have to change this when actual actions on click will be implemented */
    private fun setSnackBar(parent: View, message: String): Snackbar {
        val snackbar = Snackbar.make(parent, message, Snackbar.LENGTH_SHORT)
        snackbar.apply {
            // default toast on click
            setAction(R.string.undo) {
                Toast.makeText(
                    requireContext(), R.string.undone, Toast.LENGTH_SHORT
                ).show()
            }
            // white button color
            setActionTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.white_day_night
                )
            )
        }
        return snackbar
    }
}