package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.PlatformContext
import dev.seabat.kmp.rtdb.createDataStore
import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.usecase.CreateGuidUseCase
import dev.seabat.kmp.rtdb.usecase.FetchCustomTokenUseCase
import dev.seabat.kmp.rtdb.usecase.LoadGuidUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val guidRepository = GuidRepository(createDataStore(PlatformContext()))
    private val createGuidUseCase = CreateGuidUseCase()
    private val loadGuidUseCase = LoadGuidUseCase(guidRepository,createGuidUseCase)


    private val _customToken = MutableStateFlow("")
    val customToken: StateFlow<String>
        get() = _customToken

    private val _guid = MutableStateFlow("")
    val guid: StateFlow<String>
        get() = _guid

    val fetchCustomTokenUseCase = FetchCustomTokenUseCase(FirebaseAuthRepository())

    fun loadGuid() {
        viewModelScope.launch {
            _guid.value = loadGuidUseCase.invoke()
        }
    }

    fun fetchCustomToken(userId: String) {
        viewModelScope.launch {
            fetchCustomTokenUseCase(userId).collect { customToken ->
                _customToken.value = customToken
            }
        }
    }

    fun clearScreen() {
        _customToken.value = ""
        _guid.value = ""
    }
}