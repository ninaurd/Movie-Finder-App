package com.example.movieslookupmanual

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the parcelized object from Safe Args
        val item = args.item

        // finding the elements in the layout
        val descView = view.findViewById<TextView>(R.id.details_description)
        val detailsTopBar = view.findViewById<Toolbar>(R.id.details_toolbar)
        val posterView = view.findViewById<AppCompatImageView>(R.id.details_poster)

        // displaying data in the Views
        descView.text = item.desc
        detailsTopBar.title = item.title
        posterView.setImageResource(item.poster)

        // setting FAB icon and on click reaction
        val fabFav = view.findViewById<FloatingActionButton>(R.id.details_fab_favorites)
        fabFav.apply {
            setImageResource(
                if (item.isFav) R.drawable.ic_heart_full else R.drawable.ic_heart_empty
            )
            setOnClickListener {
                if (!item.isFav) {
                    setImageResource(R.drawable.ic_heart_full)
                    item.isFav = true
                } else {
                    setImageResource(R.drawable.ic_heart_empty)
                    item.isFav = false
                }
            }
        }

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

        // setting up share FAB with a text
        val fabShare = view.findViewById<FloatingActionButton>(R.id.details_fab_share)
        fabShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(
                    Intent.EXTRA_TEXT,
                    getString(R.string.check_it_out, item.title, item.desc)
                )
                type = "text/plain"
            }
            startActivity(Intent.createChooser(intent, getString(R.string.share_via)))
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

        // on create little animation for the box
        val giftBox = view.findViewById<ImageView>(R.id.gift_box)
        val giftBoxAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.gift_box_popping)
        giftBox.startAnimation(giftBoxAnimation)

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