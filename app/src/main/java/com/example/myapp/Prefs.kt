package com.example.myapp

import android.content.Context

class Prefs(context: Context) {

    private val prefsFileName = context.packageName
    private val numberID = "number"
    private val prefs = context.getSharedPreferences(prefsFileName, 0)

    var number: Int
        get() = prefs.getInt(numberID, 0)
        set(value) = prefs.edit().putInt(numberID, value).apply()
}
