package com.sample.marvel.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.marvel.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(toolbar)
    }
}