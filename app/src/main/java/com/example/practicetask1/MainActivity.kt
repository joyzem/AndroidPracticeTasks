package com.example.practicetask1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PracticeTask1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
        val bottomNavigationMenu: BottomNavigationView = findViewById(R.id.bottom_nav_menu)
        bottomNavigationMenu.apply {
            background = null
            menu.getItem(2).isEnabled = false
            menu.getItem(4).isChecked = true
        }
    }
}
