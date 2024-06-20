package com.ignaciotun.prueba_tecnica.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MovieAppAplication : Application() {
    private val preferencesKey = "movie_prefs"

    companion object {
        lateinit var appContext: Context
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext

        sharedPreferences = appContext.getSharedPreferences(preferencesKey, Context.MODE_PRIVATE)!!
    }
}