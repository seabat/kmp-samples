package dev.seabat.kmp.rtdb.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.repository.FirebaseAuthRepository
import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import dev.seabat.kmp.rtdb.usecase.ObserveBalanceUseCase
import dev.seabat.kmp.rtdb.usecase.SignInWithCustomTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DatabaseObservationViewModel : ViewModel() {
    private val databaseRepository = RealtimeDatabaseRepository()

    private val observeBalanceUseCase = ObserveBalanceUseCase(databaseRepository)
    val signInWithCustomTokenUseCase = SignInWithCustomTokenUseCase(FirebaseAuthRepository())

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    fun setupDatabase(token: String, userId: String, guid: String) {
        viewModelScope.launch {
            if (signInWithCustomTokenUseCase(token)) {
                try {
                    observeBalanceUseCase(userId, guid).collect { dataSnapshot ->
                        val value = dataSnapshot.value
                        if (value is Long) {
                            _balance.value = value.toString()
                        }
                    }
                } catch (e: Exception) {
                    _errorMessage.value = e.message ?: ""
                }
            }
        }
    }
}