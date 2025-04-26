package dev.seabat.kmp

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.api.aws.AWSApiPlugin
import com.amplifyframework.core.Amplify

class KmpAppSyncApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            Log.i("KmpAppSync", "Amplify の初期化に成功しました")
        } catch (error: AmplifyException) {
            Log.e("KmpAppSync", "Amplify の初期化に失敗しました", error)
        }
    }
} 