package com.example.android101.uistate

import com.example.android101.model.Item

data class AppUiState(
    val items: List<Item>,
    val selectedItem: Item,
    val searchInput: String,
    val isSearching: Boolean
)