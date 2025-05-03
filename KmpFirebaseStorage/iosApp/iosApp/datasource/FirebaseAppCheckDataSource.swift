import Foundation
import ComposeApp
import FirebaseCore
import FirebaseAppCheck

class FirebaseAppCheckDataSource: ComposeApp.FirebaseAppCheckDataSourceContract {
    
    func __activate() async throws -> FirebaseAppCheckResult {
        let providerFactory = AppCheckDebugProviderFactory()
        AppCheck.setAppCheckProviderFactory(providerFactory)
        
        // App Check プロバイダファクトリを設定してから Firebase Core を初期化すること
        FirebaseApp.configure()
        return FirebaseAppCheckResult.Success()
    }
}
