package com.milwen.blueprint

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class O2Application : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}