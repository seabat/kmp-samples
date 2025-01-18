package dev.seabat.kmp.rtdb

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

actual fun createDataStore(platformContext: PlatformContext): DataStore<Preferences> = getDataStore(
    producePath = { platformContext.context.filesDir.resolve(dataStoreFileName).absolutePath }
)
