import Foundation
import ComposeApp
import FirebaseStorage
import FirebaseCore

class FirebaseStorageDataSource: ComposeApp.FirebaseStorageDataSourceContract {
    func fetch(callback: @escaping (String?, KotlinThrowable?) -> Void) {
        
        let storage = Storage.storage(url:"gs://seabat-dev.firebasestorage.app")
        var noticeRef = storage.reference().child("notice.txt")
        
        noticeRef.getData(maxSize: 1 * 1024 * 1024) { data, error in
            if let error = error {
                print("Error downloading notice.txt: \(error)")
                let networkError = FirebaseStorageError.NetworkError(message: error.localizedDescription)
                callback(nil, networkError)
            } else {
                if let textData = data, let text = String(data: textData, encoding: .utf8) {
                    print("notice.txt content: \(text)")
                    callback(text, nil)
                } else {
                    let parseError = FirebaseStorageError.DataParseError(message: "Failed to parse data")
                    callback(nil, parseError)
                }
            }
        }
    }
}
