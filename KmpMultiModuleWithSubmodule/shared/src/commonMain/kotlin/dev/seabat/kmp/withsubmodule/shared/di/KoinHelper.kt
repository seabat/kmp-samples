package dev.seabat.kmp.withsubmodule.shared.di

import dev.seabat.kmp.tutorial.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.withsubmodule.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import dev.seabat.kmp.tutorial.shared.viewmodel.GreetingSharedViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * iOS 用に Koin の inject() を実行するクラス
 */
class KoinHelper : KoinComponent {

    // NOTE: KmpMultiModuleWithSubmodule の LoadRocketLaunchInfoUseCase を DI する場合は
    //       dev.seabat.kmp.withsubmodule.shared.usecase の LoadRocketLaunchInfoUseCaseContract を使用する。
    //       KmpTutorial の LoadRocketLaunchInfoUseCase を DI する場合は
    //       dev.seabat.kmp.tutorial.shared.usecase の LoadRocketLaunchInfoUseCaseContract を使用する。
    val loadRocketLaunchInfoUseCase: LoadRocketLaunchInfoUseCaseContract by inject()
    val grepUseCase: GrepUseCaseContract by inject()
    val greetingSharedViewModel: GreetingSharedViewModel by inject()
}

fun getGreetingSharedViewModel(): GreetingSharedViewModel {
    return KoinHelper().greetingSharedViewModel // Koin の get() をブリッジ
}

fun getLoadRocketLaunchInfoUseCase(): LoadRocketLaunchInfoUseCaseContract {
    return KoinHelper().loadRocketLaunchInfoUseCase // Koin の get() をブリッジ
}

fun getGrepUseCase(): GrepUseCaseContract {
    return KoinHelper().grepUseCase // Koin の get() をブリッジ
}
