package dev.seabat.kmp.firebasestorage.datasource

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import dev.seabat.kmp.firebasestorage.error.KmpFirebaseStorageError
import dev.seabat.kmp.firebasestorage.result.FirebaseAppCheckResult

private const val TAG = "AppCheck"

class FirebaseAppCheckDataSource : FirebaseAppCheckDataSourceContract {
    override suspend fun activate(): FirebaseAppCheckResult {

       return runCatching {
            val debug = DebugAppCheckProviderFactory.getInstance()
            FirebaseAppCheck.getInstance().installAppCheckProviderFactory(debug)

            Firebase.appCheck.getToken(false)
                .addOnSuccessListener { token ->
                    Log.d(TAG, "AppCheck Token: ${token.token}")
                }
                .addOnFailureListener { e ->
                    Log.d(TAG, "Failed to get AppCheck token: ${e.message}")
                }
        }.fold(
            onSuccess = {
                FirebaseAppCheckResult.Success
            },
            onFailure = { e ->
                Log.e(TAG, "Failed to activate AppCheck: ${e.message}", e)
                FirebaseAppCheckResult.Error(
                    KmpFirebaseStorageError.AppCheckActivationFailure(e.message ?: "Failed to activate AppCheck")
                )
            }
        )
    }
}