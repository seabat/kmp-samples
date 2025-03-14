package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import dev.seabat.kmp.rtdb.usecase.FetchCustomTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _customToken = MutableStateFlow("")
    val customToken: StateFlow<String>
        get() = _customToken

    val fetchCustomTokenUseCase = FetchCustomTokenUseCase(FirebaseAuthRepository())

    fun fetchCustomToken(userId: String) {
        viewModelScope.launch {
            fetchCustomTokenUseCase(userId).collect { customToken ->
                _customToken.value = customToken
            }
        }
    }

    fun clearToken() {
        _customToken.value = ""
    }
}