package com.example.movieslookupmanual

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.movieslookupmanual.data.GeneralRVType
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

        // adding and launching the fragment only once - when the activity starts
        // as it's the first fragment, not adding to the BackStack
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_placeholder, MainFragment())
                .commit()
        }

        // toolbar toast - navigation
        val regularToolbar = findViewById<MaterialToolbar>(R.id.top_toolbar)
        regularToolbar.setNavigationOnClickListener {
            Toast.makeText(this, R.string.navigation_toast, Toast.LENGTH_SHORT).show()
        }

        // toolbar items toasts
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

        // bottom navigation bar toasts
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
    }

    // separate function for transferring data bit by bit to the second fragment and opening it
    fun launchDetailsFragment(item: GeneralRVType) {
        val bundle = Bundle().apply {
            putString("desc", item.desc)
            putString("title", item.title)
            putInt("poster", item.poster)
        }

        val fragment = DetailsFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }
}