package com.example.movieslookupmanual

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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

        findViewById<ImageView>(R.id.walle).setOnClickListener {
            Toast.makeText(this, getString(R.string.walle_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.closer).setOnClickListener {
            Toast.makeText(this, getString(R.string.closer_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.joker).setOnClickListener {
            Toast.makeText(this, getString(R.string.joker_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.seven_pounds).setOnClickListener {
            Toast.makeText(this, getString(R.string.seven_pounds_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.shutter_island).setOnClickListener {
            Toast.makeText(this, getString(R.string.shutter_island_toast), Toast.LENGTH_SHORT)
                .show()
        }
        findViewById<ImageView>(R.id.sixth_sense).setOnClickListener {
            Toast.makeText(this, getString(R.string.sixth_sense_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.leon).setOnClickListener {
            Toast.makeText(this, getString(R.string.leon_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.totoro).setOnClickListener {
            Toast.makeText(this, getString(R.string.totoro_toast), Toast.LENGTH_SHORT).show()
        }
        findViewById<ImageView>(R.id.the_green_mile).setOnClickListener {
            Toast.makeText(this, getString(R.string.green_mile_toast), Toast.LENGTH_SHORT).show()
        }
    }
}