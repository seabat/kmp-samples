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
        // NOTE: KmpMultiModuleWithSubmodule プロジェクトの CommonModule.kt と
        //       KmpTutorial プロジェクトの CommonModule.kt が被るので、iOS ビルドによって
        //       前者は CommonModuleKt_ 、後者は CommonModuleKt という swift name になる。
        CommonModuleKt_.doInitIosKoin()
    }
}
