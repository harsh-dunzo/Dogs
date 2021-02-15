package com.example.dogs.util

import android.app.Application
import android.content.Context
import com.example.dogs.roomdb.DogDatabase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DogDatabase.initDB(applicationContext)
    }
}