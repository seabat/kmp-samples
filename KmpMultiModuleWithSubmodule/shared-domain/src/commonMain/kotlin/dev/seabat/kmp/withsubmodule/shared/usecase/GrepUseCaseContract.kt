package dev.seabat.kmp.withsubmodule.shared.usecase

interface GrepUseCaseContract {
    operator fun invoke(lines: List<String>, pattern: String, action: (String) -> Unit)
}