package com.example.newbees

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import com.example.newbees.utils.TodoTheme
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication:Application(){

    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
            .getString("theme", "FOLLOW_SYSTEM")

        updateTheme(enumValueOf(preference as String))

    }

    fun updateTheme(theme: TodoTheme){
        AppCompatDelegate.setDefaultNightMode(when(theme){

            TodoTheme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            TodoTheme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
            TodoTheme.FOLLOW_SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

        })

    }
}