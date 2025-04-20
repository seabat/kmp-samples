package dev.seabat.kmp.firebasestorage.datasource

interface FirebaseAppCheckDataSourceContract {
    fun activate(callback: (Boolean, Throwable?) -> Unit)
}

fun FirebaseAppCheckDataSourceCallback() {

}