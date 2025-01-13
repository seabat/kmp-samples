package dev.seabat.kmp.rtdb

import android.app.Application

class RtdbApplication : Application() {

    companion object {
        lateinit var instance: RtdbApplication;
        fun getApplicationInstance(): RtdbApplication {
            return instance;
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}