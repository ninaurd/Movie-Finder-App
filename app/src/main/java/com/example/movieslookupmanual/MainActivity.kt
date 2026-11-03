package com.example.movieslookupmanual

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
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

        // exit alert dialog
        onBackPressedDispatcher.addCallback(this) {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                AlertDialog.Builder(
                    ContextThemeWrapper(
                        this@MainActivity,
                        R.style.ExitAlertDialogStyle
                    )
                )
                    .setTitle(R.string.really_exit)
                    .setMessage(R.string.please_dont_exit)
                    .setIcon(R.drawable.ic_exit)
                    .setPositiveButton(R.string.yes) { _, _ -> finish() }
                    .setNegativeButton(R.string.no) { _, _ ->
                        Toast.makeText(this@MainActivity, R.string.good, Toast.LENGTH_SHORT)
                            .show()
                    }
                    .setNeutralButton(R.string.dunno) { _, _ ->
                        Toast.makeText(this@MainActivity, R.string.think_well, Toast.LENGTH_SHORT)
                            .show()
                    }
                    .show()
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