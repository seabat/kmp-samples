package dev.seabat.kmp.rtdb

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

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

        Firebase.initialize(this)
    }
}