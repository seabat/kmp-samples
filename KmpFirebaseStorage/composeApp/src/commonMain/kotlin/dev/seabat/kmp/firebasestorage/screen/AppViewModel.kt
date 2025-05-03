package dev.seabat.kmp.firebasestorage.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.firebasestorage.result.FirebaseStorageResult
import dev.seabat.kmp.firebasestorage.usecase.FetchNoticeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val fetchNoticeUseCase: FetchNoticeUseCase
) : ViewModel() {

    private val _notice: MutableStateFlow<FirebaseStorageResult> =
        MutableStateFlow(FirebaseStorageResult.Success(""))
    val notice: StateFlow<FirebaseStorageResult>
        get() = _notice

    private val _message: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String>
        get() = _message

    fun fetchNotice() {
        viewModelScope.launch {
            fetchNoticeUseCase.invoke().collect { result ->
                _notice.value = result
            }
        }
    }

    fun clearError() {
        _notice.value = FirebaseStorageResult.Success("")
    }
}