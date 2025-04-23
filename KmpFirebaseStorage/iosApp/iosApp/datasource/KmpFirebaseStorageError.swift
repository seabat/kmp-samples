import Foundation
import ComposeApp

enum KmpFirebaseStorageError: Error {
    case FirebaseStorageFailure(message: String)
    case FirebaseStorageDataParse(message: String)
    
    var message: String {
        switch self {
        case .FirebaseStorageFailure(let message):
            return message
        case .FirebaseStorageDataParse(let message):
            return message
        }
    }
} 