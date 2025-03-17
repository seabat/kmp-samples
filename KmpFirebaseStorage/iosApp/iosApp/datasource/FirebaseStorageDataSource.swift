import Foundation
import ComposeApp
import FirebaseStorage
import FirebaseCore

class FirebaseStorageDataSource: ComposeApp.FirebaseStorageDataSourceContract {
    func fetch(callback: @escaping (String?, KotlinThrowable?) -> Void) {
        callback(String("iOSのお知らせ"), nil)
    }
}
