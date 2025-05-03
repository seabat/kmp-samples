import ComposeApp

class SwiftLibDependencyFactory: SwiftLibDependencyFactoryContract {

    static var shared = SwiftLibDependencyFactory()

    func provideTestDataSource() -> any TestDataSourceContract {
        return TestDataSource()
    }
    
    func provideFirebaseStorageDataSource() -> any FirebaseStorageDataSourceContract {
        return FirebaseStorageDataSource()
    }

    func provideFirebaseAppCheckDataSource() -> any FirebaseAppCheckDataSourceContract {
        return FirebaseAppCheckDataSource()
    }
}
