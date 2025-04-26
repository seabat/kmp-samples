package dev.seabat.kmp.firebasestorage.di

import dev.seabat.kmp.firebasestorage.usecase.ActivateAppCheckUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * iOS 用に Koin の inject() を実行するクラス
 */
class KoinHelper : KoinComponent {
    val activateAppCheckUseCase: ActivateAppCheckUseCase by inject()
}

fun getActivateAppCheckUseCase(): ActivateAppCheckUseCase {
    return KoinHelper().activateAppCheckUseCase
}
