package com.example.android101.model

import androidx.annotation.StringRes

data class Item(
    @StringRes val question: Int,
    @StringRes val answer: Int,
    @StringRes val title: Int
)