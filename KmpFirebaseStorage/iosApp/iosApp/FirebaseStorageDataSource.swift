import Foundation
import ComposeApp
import FirebaseStorage
import FirebaseCore

class FirebaseStorageDataSource: ComposeApp.FirebaseStorageDataSourceContract {
    func fetch() -> String {
        return "iOSのお知らせ"
    }
}
