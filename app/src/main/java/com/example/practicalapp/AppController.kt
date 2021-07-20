package com.example.practicalapp

import android.app.Application
import com.example.practicalapp.api.Repostiary

class AppController:Application() {

    override fun onCreate() {
        super.onCreate()
        Repostiary.buildService()
    }
}