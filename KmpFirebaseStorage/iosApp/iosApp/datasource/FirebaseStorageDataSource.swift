import Foundation
import ComposeApp
import FirebaseStorage
import FirebaseCore

class FirebaseStorageDataSource: ComposeApp.FirebaseStorageDataSourceContract {
    func fetch(callback: @escaping (FirebaseStorageResult) -> Void) {
        
        let storage = Storage.storage(url:"gs://seabat-dev.firebasestorage.app")
        var noticeRef = storage.reference().child("notice.txt")
        
        noticeRef.getData(maxSize: 1 * 1024 * 1024) { data, error in
            if let error = error {
                print("Error downloading notice.txt: \(error)")
                let networkError = KmpFirebaseStorageError.FirebaseStorageFailure(message: error.localizedDescription)
                callback(FirebaseStorageResult.Error(error: networkError as! ComposeApp.KmpFirebaseStorageError))
            } else {
                if let textData = data, let text = String(data: textData, encoding: .utf8) {
                    print("notice.txt content: \(text)")
                    callback(FirebaseStorageResult.Success(notice: text))
                } else {
                    let parseError = KmpFirebaseStorageError.FirebaseStorageDataParse(message: "Failed to parse data")
                    callback(FirebaseStorageResult.Error(error: parseError as! ComposeApp.KmpFirebaseStorageError))
                }
            }
        }
    }
}
