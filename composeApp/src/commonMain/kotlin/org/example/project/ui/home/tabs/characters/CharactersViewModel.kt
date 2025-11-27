package org.example.project.ui.home.tabs.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.project.domain.usecase.GetRandomCharacter
import org.example.project.domain.Repository

class CharactersViewModel(
    private val getCharacter: GetRandomCharacter,
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow<CharactersState>(CharactersState())
    val state: StateFlow<CharactersState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getCharacter()
            }
            _state.update { state ->
                state.copy(characterOfTheDay = result)
            }
        }
        getAllCharacters()
    }

    private fun getAllCharacters() {
        _state.update { state ->
            state.copy(characters = repository.getAllCharacters().cachedIn(viewModelScope))
        }
    }

}