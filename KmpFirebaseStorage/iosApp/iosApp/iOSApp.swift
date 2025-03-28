import SwiftUI
import ComposeApp
import FirebaseCore
import FirebaseStorage

@main
struct iOSApp: App {
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }

    init() {
        CommonModuleKt.doInitIosKoin(
            onKoinStart: {
                IosModuleKt.createSwiftLibDependencyModule(
                    factory: SwiftLibDependencyFactory.shared
                )
            }
        )
    }
}

class AppDelegate: UIResponder, UIApplicationDelegate {

    func application(_ application: UIApplication,
                     didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        FirebaseApp.configure()
        
        return true
    }
}
