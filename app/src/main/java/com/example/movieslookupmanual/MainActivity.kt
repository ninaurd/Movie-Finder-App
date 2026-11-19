package com.example.movieslookupmanual

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // preparing the navigation controller for toolbars
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


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
                    navController.navigate(R.id.action_to_favourites)
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
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
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
}