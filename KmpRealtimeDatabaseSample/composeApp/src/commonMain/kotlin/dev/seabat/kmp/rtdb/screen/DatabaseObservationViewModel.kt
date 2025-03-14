package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.PlatformContext
import dev.seabat.kmp.rtdb.createDataStore
import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import dev.seabat.kmp.rtdb.usecase.CreateGuidUseCase
import dev.seabat.kmp.rtdb.usecase.LoadGuidUseCase
import dev.seabat.kmp.rtdb.usecase.ObserveBalanceUseCase
import dev.seabat.kmp.rtdb.usecase.RegisterDatabaseEventUseCase
import dev.seabat.kmp.rtdb.usecase.SignInWithCustomTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DatabaseObservationViewModel : ViewModel() {
    private val guidRepository = GuidRepository(createDataStore(PlatformContext()))
    private val databaseRepository = RealtimeDatabaseRepository()

    private val createGuidUseCase = CreateGuidUseCase()
    private val observeBalanceUseCase = ObserveBalanceUseCase(databaseRepository)
    private val registerDatabaseEventUseCase = RegisterDatabaseEventUseCase(databaseRepository)
    private val loadGuidUseCase = LoadGuidUseCase(guidRepository,createGuidUseCase)
    val signInWithCustomTokenUseCase = SignInWithCustomTokenUseCase(FirebaseAuthRepository())

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun setupDatabase(token: String, userId: String) {
        viewModelScope.launch {
            if (signInWithCustomTokenUseCase(token)) {
                // val guid = loadGuidUseCase()
                try {
                    registerDatabaseEventUseCase(userId) {
                        observeBalanceUseCase(it).collect { dataSnapshot ->
                            val value = dataSnapshot.value
                            if (value is Long) {
                                _balance.value = value.toString()
                            }
                        }
                    }
                } catch (e: Exception) {
                    _errorMessage.value = e.message ?: ""
                }
            }
        }
    }
}