package dev.seabat.kmp.rtdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import dev.seabat.kmp.rtdb.usecase.CreateGuidUseCase
import dev.seabat.kmp.rtdb.usecase.ObserveBalanceUseCase
import dev.seabat.kmp.rtdb.usecase.RegisterDatabaseEventUseCase
import dev.seabat.kmp.rtdb.usecase.RoadGuidUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    private val guidRepository = GuidRepository(createDataStore(PlatformContext()))
    private val databaseRepository = RealtimeDatabaseRepository()

    private val createGuidUseCase = CreateGuidUseCase()
    private val observeBalanceUseCase = ObserveBalanceUseCase(databaseRepository)
    private val registerDatabaseEventUseCase = RegisterDatabaseEventUseCase(databaseRepository)
    private val roadGuidUseCase = RoadGuidUseCase(guidRepository,createGuidUseCase)

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    fun setupDatabase() {
        viewModelScope.launch {
            val guid = roadGuidUseCase()
            registerDatabaseEventUseCase(guid) {
                observeBalanceUseCase(it).collect { dataSnapshot ->
                    val value = dataSnapshot.value
                    if (value is Long) {
                        _balance.value = value.toString()
                    }
                }
            }
        }
    }
}