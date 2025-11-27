package org.example.project.ui.home.tabs.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.example.project.domain.Repository

class EpisodesViewModel(repository: Repository): ViewModel() {


    private val _state = MutableStateFlow(EpisodesState())
    val state: StateFlow<EpisodesState> = _state.asStateFlow()

    init {
        _state.update { state ->
            state.copy(episodes = repository.getAllEpisodes().cachedIn(viewModelScope))
        }
    }

    fun onPlaySelected(url: String) {
        _state.update { state ->
            state.copy(playVideo = url)
        }
    }

    fun onCloseVideo() {
        _state.update { state ->
            state.copy(playVideo = "")
        }
    }

}