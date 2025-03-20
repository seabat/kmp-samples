package dev.seabat.kmp.firebasestorage.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.firebasestorage.usecase.FetchNoticeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val fetchNoticeUseCase: FetchNoticeUseCase
) : ViewModel() {

    private val _notice = MutableStateFlow("")
    val notice: StateFlow<String>
        get() = _notice

    fun fetchNotice() {
        viewModelScope.launch {
            fetchNoticeUseCase.invoke().collect {
                _notice.value = it
            }
        }
    }
}