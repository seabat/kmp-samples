package dev.seabat.kmp.multimodule.page.rocketlaunch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.seabat.kmp.multimodule.shared.RocketLaunchShared
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RocketLaunchViewModel : ViewModel() {
    private val _rocketLaunchPhrase = MutableStateFlow("")
    val rocketLaunchPhrase: StateFlow<String>
        get() = _rocketLaunchPhrase

    init {
        viewModelScope.launch {
            RocketLaunchShared().getLaunchPhraseFlow().collect { phrase ->
                _rocketLaunchPhrase.update {
                    phrase
                }
            }
        }
    }
}