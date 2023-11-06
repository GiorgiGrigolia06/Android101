package com.example.android101.uistate

import androidx.compose.ui.text.input.TextFieldValue
import com.example.android101.model.Item

data class AppUiState(
    val items: List<Item>,
    val selectedItem: Item,
    val searchInput: TextFieldValue,
    val isSearching: Boolean
)