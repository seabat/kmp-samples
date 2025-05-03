import Foundation
import ComposeApp
import FirebaseStorage
import FirebaseCore



class FirebaseStorageDataSource: ComposeApp.FirebaseStorageDataSourceContract {
    
    // NOTE: FirebaseStorageDataSource の suspend fun fetch() は  func __fetch() async throws として実装する
    func __fetch() async throws -> FirebaseStorageResult {
        let storage = Storage.storage(url:"gs://seabat-dev.firebasestorage.app")
        let noticeRef = storage.reference().child("notice.txt")

        return try await withCheckedThrowingContinuation { continuation in
            noticeRef.getData(maxSize: 1 * 1024 * 1024) { data, error in
                if let error = error {
                    print("Error downloading notice.txt: \(error)")
                    let networkError = KmpFirebaseStorageError.FirebaseStorageFailure(originalMessage: error.localizedDescription)
                    continuation.resume(returning: FirebaseStorageResult.Error(error: networkError as ComposeApp.KmpFirebaseStorageError))
                } else {
                    if let textData = data {
                        if let text = String(data: textData, encoding: .utf8) {
                            print("notice.txt content: \(text)")
                            continuation.resume(returning: FirebaseStorageResult.Success(notice: text))
                        } else {
                            let parseError = KmpFirebaseStorageError.FirebaseStorageDataParse(originalMessage: "Failed to parse data")
                            continuation.resume(returning: FirebaseStorageResult.Error(error: parseError as ComposeApp.KmpFirebaseStorageError))
                        }
                    } else {
                        continuation.resume(
                            throwing: NSError(
                                domain: "dev.seabat.kmp.firebasestorage",
                                code: 123, // 適当なエラーコード
                                userInfo: [NSLocalizedDescriptionKey: "This is an intentional test error for __fetch()"]
                            )
                        )
                    }
                }
            }
        }
     }
}
