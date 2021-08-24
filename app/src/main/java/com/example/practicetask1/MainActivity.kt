package com.example.practicetask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.practicetask1.fragments.HelpFragment
import com.example.practicetask1.fragments.ProfileFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PracticeTask1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigationBarView = findViewById<NavigationBarView>(R.id.bottom_nav_menu).apply {
            background = null
            menu.getItem(2).isEnabled = false
            menu.getItem(2).isChecked = true
            setOnItemSelectedListener {
                when(it.itemId) {
                    R.id.profile-> navToFragment(ProfileFragment())
                }
                true
            }
        }

        val fab = findViewById<FloatingActionButton>(R.id.help_fab)
        fab.setOnClickListener {
            navToFragment(HelpFragment())
            navigationBarView.menu.getItem(2).isChecked = true
        }
    }

    private fun navToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .setReorderingAllowed(true)
            .commit()
    }
}
