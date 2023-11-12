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

    fun updateSearchInput(input: String) {
        _uiState.update {
            it.copy(
                searchInput = input
            )
        }
    }
}