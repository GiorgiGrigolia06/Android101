package com.example.android101.ui

import androidx.lifecycle.ViewModel
import com.example.android101.data.appData
import com.example.android101.model.Item
import com.example.android101.uistate.AppUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        AppUiState(
            items = appData,
            selectedItem = appData[0],
            isSearching = false,
            searchInput = ""
        )
    )

    val uiState: StateFlow<AppUiState> = _uiState.asStateFlow()

    fun updateSelectedItem(item: Item) {
        _uiState.update {
            it.copy(
                selectedItem = item
            )
        }
    }

    fun toggleSearch() {
        _uiState.update {
            it.copy(
                isSearching = !_uiState.value.isSearching,
                searchInput = ""
            )
        }
    }

    fun toggleSearchOnQuestionsScreenClick() {
        _uiState.update {
            it.copy(
                isSearching = false
            )
        }
    }

    fun updateSearchInput(input: String) {
        _uiState.update {
            it.copy(
                searchInput = input
            )
        }
    }

    fun getFilteredQuestions(questionStrings: List<String>): List<Item> {
        return if (_uiState.value.searchInput.isNotBlank())
            _uiState.value.items.filterIndexed { index, _ ->
                questionStrings.getOrNull(index)?.contains(
                    _uiState.value.searchInput.trim(),
                    ignoreCase = true
                ) == true
            }
        else _uiState.value.items
    }
}