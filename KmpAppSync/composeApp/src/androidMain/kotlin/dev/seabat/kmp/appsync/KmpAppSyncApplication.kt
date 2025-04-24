package dev.seabat.kmp.appsync

import android.app.Application
import com.amplifyframework.core.Amplify
import com.amplifyframework.api.aws.AWSApiPlugin

class KmpAppSyncApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            Amplify.addPlugin(AWSApiPlugin())
            Amplify.configure(applicationContext)
            println("Amplify の初期化に成功しました")
        } catch (error: Exception) {
            println("Amplify の初期化に失敗しました: " + error.message)
        }
    }
} 