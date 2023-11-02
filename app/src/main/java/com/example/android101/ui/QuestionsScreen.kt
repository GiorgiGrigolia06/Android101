package com.example.android101.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.android101.R
import com.example.android101.data.appData
import com.example.android101.model.Item
import com.example.android101.ui.theme.Android101Theme

@Composable
fun QuestionsScreen(
    items: List<Item>,
    navigateToContent: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.lazy_column_horizontal_padding)),
        modifier = modifier
    ) {
        items(items) { item ->
            Question(
                item = item,
                navigateToContent = { navigateToContent(item) }
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Question(
    item: Item,
    navigateToContent: (Item) -> Unit,
    modifier: Modifier = Modifier
) {
    ListItem(
        headlineText = {
            Text(
                text = stringResource(id = item.question),
                modifier = Modifier.fillMaxWidth(0.9f)
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.clickable { navigateToContent(item) }
            )
        },
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surface,
            headlineColor = MaterialTheme.colorScheme.onSurface,
            trailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shadowElevation = dimensionResource(id = R.dimen.list_item_shadow_elevation),
        modifier = modifier
            .padding(vertical = dimensionResource(id = R.dimen.list_item_vertical_padding))
            .clickable { navigateToContent(item) }
    )
}

@Preview(showBackground = true)
@Composable
fun QuestionScreenPreview() {
    Android101Theme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            QuestionsScreen(appData, { })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionPreview() {
    Android101Theme {
        Question(
            Item(R.string.abstraction_question, R.string.abstraction_answer, R.string.abstraction_title),
            { }
        )
    }
}