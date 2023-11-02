package com.example.android101.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android101.data.appData
import com.example.android101.model.Item
import com.example.android101.ui.theme.Android101Theme

@Composable
fun AnswerScreen(
    item: Item,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = item.answer),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AnswerScreenPreview() {
    Android101Theme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            AnswerScreen(appData[19])
        }
    }
}