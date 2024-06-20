package com.ignaciotun.prueba_tecnica.core

import android.content.SharedPreferences
import com.ignaciotun.prueba_tecnica.application.MovieAppAplication
import com.ignaciotun.prueba_tecnica.presentation.ui.Login

object PreferencesHelper {
    private var preferencesHelper: PreferencesHelper? = null
    private var sharedPreferences: SharedPreferences? = null

    fun getPreferences(): PreferencesHelper? {
        if (preferencesHelper == null) {
            preferencesHelper = preferencesHelper
        }
        sharedPreferences = MovieAppAplication.sharedPreferences
        return preferencesHelper
    }

    fun setLogin(login: Boolean){
        val editor = sharedPreferences!!.edit()
        editor.putBoolean("login", login)
        editor.apply()
    }

    fun getLogin(): Boolean {
        return sharedPreferences!!.getBoolean("login", false)
    }
}