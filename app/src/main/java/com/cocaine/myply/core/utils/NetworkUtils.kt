package com.cocaine.myply.core.utils

import android.content.Context
import android.provider.Settings

fun getDeviceToken(applicationContext: Context): String {
    return Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ANDROID_ID)
}