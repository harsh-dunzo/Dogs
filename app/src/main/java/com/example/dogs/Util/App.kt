package com.example.dogs.Util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.dogs.roomdb.DogDatabase

class App : Application() {

    private lateinit var context:Context;

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
        DogDatabase.initDB(context)

    }




}