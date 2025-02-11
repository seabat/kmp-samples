package dev.seabat.kmp.multimodule.shared.di

import dev.seabat.kmp.multimodule.shared.usecase.GrepUseCaseContract
import dev.seabat.kmp.multimodule.shared.usecase.LoadRocketLaunchInfoUseCaseContract
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * iOS 用に Koin の inject() を実行するクラス
 */
class KoinHelper : KoinComponent {
    val loadRocketLaunchInfoUseCase: LoadRocketLaunchInfoUseCaseContract by inject()
    val grepUseCase: GrepUseCaseContract by inject()
}

fun getLoadRocketLaunchInfoUseCase(): LoadRocketLaunchInfoUseCaseContract {
    return KoinHelper().loadRocketLaunchInfoUseCase // Koin の get() をブリッジ
}

fun getGrepUseCase(): GrepUseCaseContract {
    return KoinHelper().grepUseCase // Koin の get() をブリッジ
}
