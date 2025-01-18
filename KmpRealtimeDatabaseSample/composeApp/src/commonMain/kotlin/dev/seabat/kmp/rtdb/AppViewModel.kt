package dev.seabat.kmp.rtdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.repository.GuidRepository
import dev.seabat.kmp.rtdb.repository.RealtimeDatabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(
    private val repository: GuidRepository,
    private val realtimeDatabaseRepository: RealtimeDatabaseRepository
) : ViewModel() {

    private val _guid = MutableStateFlow("")
    val guid = _guid.asStateFlow()

    private val _balance = MutableStateFlow("")
    val balance = _balance.asStateFlow()

    fun savaGuid(guid: String) {
        viewModelScope.launch {
            repository.save(guid)
        }
    }
    fun loadGuid() {
        viewModelScope.launch {
            _guid.value = repository.load()
        }
    }

    fun readDatabase() {
        viewModelScope.launch {
            realtimeDatabaseRepository.read().collect { dataSnapshot ->
                val value = dataSnapshot.value
                if (value is Long) {
                    _balance.value = value.toString()
                }
            }
        }
    }

    fun writeDatabase() {
        viewModelScope.launch {
            realtimeDatabaseRepository.write()
        }
    }
}