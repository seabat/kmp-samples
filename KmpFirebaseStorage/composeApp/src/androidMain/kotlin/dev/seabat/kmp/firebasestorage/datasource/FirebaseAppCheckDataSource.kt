package dev.seabat.kmp.firebasestorage.datasource

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory

private const val TAG = "AppCheck"

class FirebaseAppCheckDataSource : FirebaseAppCheckDataSourceContract {
    override fun activate(callback: (Boolean, Throwable?) -> Unit) {
        try {
            val debug = DebugAppCheckProviderFactory.getInstance()
            FirebaseAppCheck.getInstance().installAppCheckProviderFactory(debug)

            Firebase.appCheck.getToken(false)
                .addOnSuccessListener { token ->
                    Log.d(TAG, "AppCheck Token: ${token.token}")
                }
                .addOnFailureListener { e ->
                    Log.d(TAG, "Failed to get AppCheck token: ${e.message}")
                }

            callback(true, null)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to activate AppCheck: ${e.message}", e)
            callback(false, e)
        }
    }
}