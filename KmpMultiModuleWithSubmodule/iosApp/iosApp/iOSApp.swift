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
        // NOTE: KmpMultiModuleWithSubmodule プロジェクトと KmpTutorial プロジェクトで定義が被る場合は
        //       ハイフンをどちらかの定義の語尾に付ける。
        CommonModuleKt_.doInitIosKoin()
    }
}
