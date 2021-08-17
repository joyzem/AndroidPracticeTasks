package com.example.practicetask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PracticeTask1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
    }
}