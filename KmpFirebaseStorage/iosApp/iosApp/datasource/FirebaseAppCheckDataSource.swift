import Foundation
import ComposeApp
import FirebaseCore
import FirebaseAppCheck

class FirebaseAppCheckDataSource: ComposeApp.FirebaseAppCheckDataSourceContract {
    
    func activate(callback: @escaping (FirebaseAppCheckResult) -> Void) {
        let providerFactory = AppCheckDebugProviderFactory()
        AppCheck.setAppCheckProviderFactory(providerFactory)
        
        // App Check プロバイダファクトリを設定してから Firebase Core を初期化すること
        FirebaseApp.configure()
        callback(FirebaseAppCheckResult.Success())
    }
} 
