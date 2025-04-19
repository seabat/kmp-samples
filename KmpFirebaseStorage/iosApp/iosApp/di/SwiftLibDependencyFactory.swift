import Foundation
import ComposeApp
import SwiftUI
import UIKit

class SwiftLibDependencyFactory: SwiftLibDependencyFactoryContract {
    static var shared = SwiftLibDependencyFactory()

    func provideFirebaseStorageDataSource() -> any FirebaseStorageDataSourceContract {
        return FirebaseStorageDataSource()
    }

    func provideFirebaseAppCheckDataSource() -> any FirebaseAppCheckDataSourceContract {
        return FirebaseAppCheckDataSource()
    }
}