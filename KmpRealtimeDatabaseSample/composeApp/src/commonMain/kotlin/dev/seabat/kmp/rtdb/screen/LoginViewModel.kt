package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.PlatformContext
import dev.seabat.kmp.rtdb.createDataStore
import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.repository.UserIdRepository
import dev.seabat.kmp.rtdb.usecase.CreateGuidUseCase
import dev.seabat.kmp.rtdb.usecase.CreateUserRecordUseCase
import dev.seabat.kmp.rtdb.usecase.FetchCustomTokenUseCase
import dev.seabat.kmp.rtdb.usecase.LoadGuidUseCase
import dev.seabat.kmp.rtdb.usecase.LoadUserIdUseCase
import dev.seabat.kmp.rtdb.usecase.SaveGuidUseCase
import dev.seabat.kmp.rtdb.usecase.SaveUserIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val guidRepository = GuidRepository(createDataStore(PlatformContext()))
    private val createGuidUseCase = CreateGuidUseCase(guidRepository)
    private val saveGuidUseCase = SaveGuidUseCase(guidRepository)
    private val loadGuidUseCase = LoadGuidUseCase(guidRepository)
    private val userIdRepository = UserIdRepository(createDataStore(PlatformContext()))
    private val loadUserIdUseCase = LoadUserIdUseCase(userIdRepository)
    private val saveUserIdUseCase = SaveUserIdUseCase(userIdRepository)
    private val fetchCustomTokenUseCase = FetchCustomTokenUseCase(FirebaseAuthRepository())
    private val createUserRecordUseCase = CreateUserRecordUseCase(FirebaseAuthRepository())

    private val _customToken = MutableStateFlow("")
    val customToken: StateFlow<String>
        get() = _customToken

    private val _userId = MutableStateFlow("")
    val userId: StateFlow<String>
        get() = _userId

    private val _guid = MutableStateFlow("")
    val guid: StateFlow<String>
        get() = _guid

    fun loadGuid() {
        viewModelScope.launch {
            _guid.value = loadGuidUseCase.invoke()
        }
    }

    fun createGuid() {
        viewModelScope.launch {
            _guid.value = createGuidUseCase.invoke()
        }
    }

    fun saveGuid(guid: String) {
        viewModelScope.launch {
            saveGuidUseCase.invoke(guid)
        }
    }

    fun loadUserId() {
        viewModelScope.launch {
            _userId.value = loadUserIdUseCase.invoke()
        }
    }

    fun saveUserId(userId: String) {
        viewModelScope.launch {
            saveUserIdUseCase.invoke(userId)
        }
    }

    fun updateUserId(userId: String) {
        _userId.value = userId
    }

    fun fetchCustomToken(userId: String, createFlag: Boolean) {
        viewModelScope.launch {
            if (createFlag) {
                createUserRecordUseCase.invoke(userId, _guid.value).collect {
                    fetchCustomTokenUseCase(userId).collect { customToken ->
                        _customToken.value = customToken
                    }
                }
            } else {
                fetchCustomTokenUseCase(userId).collect { customToken ->
                    _customToken.value = customToken
                }
            }
        }
    }

    fun clearScreen() {
        _customToken.value = ""
        _guid.value = ""
    }
}