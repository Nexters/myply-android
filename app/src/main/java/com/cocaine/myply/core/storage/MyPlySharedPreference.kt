package com.cocaine.myply.core.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class MyPlySharedPreference(context: Context) {
    private val fileName = "MYPLY_SHARED"
    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    fun setValue(key: String, value: String) {
        sharedPreferences.edit {
            putString(key, value)
            this.commit()
        }
    }

    fun getValue(key: String): String {
        return sharedPreferences.getString(key, "") ?: ""
    }
}