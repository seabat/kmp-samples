package dev.seabat.kmp.rtdb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.rtdb.repository.GuidRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppViewModel(private val repository: GuidRepository) : ViewModel() {

    private val _guid = MutableStateFlow("")
    val guid = _guid.asStateFlow()

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
}