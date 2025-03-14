package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.PlatformContext
import dev.seabat.kmp.rtdb.createDataStore
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.repository.UserIdRepository
import dev.seabat.kmp.rtdb.usecase.CreateGuidUseCase
import dev.seabat.kmp.rtdb.usecase.LoadGuidUseCase
import dev.seabat.kmp.rtdb.usecase.LoadUserIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InitViewModel : ViewModel() {
    private val guidRepository = GuidRepository(createDataStore(PlatformContext()))
    private val loadGuidUseCase = LoadGuidUseCase(guidRepository)
    private val userIdRepository = UserIdRepository(createDataStore(PlatformContext()))
    private val loadUserIdUseCase = LoadUserIdUseCase(userIdRepository)

    private val _hasUserId = MutableStateFlow(false)
    val hasUserId: StateFlow<Boolean>
        get() = _hasUserId

    private val _hasGuid = MutableStateFlow(false)
    val hasGuid: StateFlow<Boolean>
        get() = _hasGuid

    fun loadUserId() {
        viewModelScope.launch {
            val userId = loadUserIdUseCase.invoke()
            _hasUserId.value = userId.isNotEmpty()
        }
    }

    fun loadGuid() {
        viewModelScope.launch {
            val guid = loadGuidUseCase.invoke()
            _hasGuid.value = guid.isNotEmpty()
        }
    }
}