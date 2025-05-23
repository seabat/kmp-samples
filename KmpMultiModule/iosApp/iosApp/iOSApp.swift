import SwiftUI
import Shared

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: ContentView.ViewModel())
        }
    }
    
    init() {
        CommonModuleKt.doInitIosKoin()
    }
}
