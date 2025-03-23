package dev.seabat.kmp.firebasestorage.datasource

class FakeFirebaseStorageDataSource(
    private val notice: String?,
    private val throwable: Throwable?
) : FirebaseStorageDataSourceContract {
    override fun fetch(callback: (String?, Throwable?) -> Unit) {
        callback(notice, throwable)
    }
}