package com.example.practicalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)

        btnUser.setOnClickListener {
            startActivity(Intent(this,PaginationActivity::class.java))
        }

        btnGame.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}