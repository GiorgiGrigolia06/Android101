package com.example.android101.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.android101.R
import com.example.android101.destinations.Destinations
import com.example.android101.ui.theme.Android101Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Android101App(
    viewModel: AppViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destinations.valueOf(
        backStackEntry?.destination?.route ?: Destinations.QUESTION.name
    )

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    if (currentScreen == Destinations.QUESTION && !uiState.isSearching)
                        Text(text = stringResource(id = R.string.app_name))

                    if (currentScreen == Destinations.QUESTION && uiState.isSearching)
                        SearchBar(
                            value = uiState.searchInput,
                            onValueChange = { viewModel.updateSearchInput(it) },
                            modifier = Modifier.fillMaxWidth()
                        )

                    if (currentScreen == Destinations.ANSWER)
                        Text(text = stringResource(id = uiState.selectedItem.title))
                },
                navigationIcon = {
                    if (currentScreen != Destinations.QUESTION) {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = null
                            )
                        }
                    }
                },
                actions = {
                    if (currentScreen == Destinations.QUESTION && !uiState.isSearching) {
                        IconButton(onClick = { viewModel.toggleSearch() }) {
                            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
                        }
                    }

                    if (currentScreen == Destinations.QUESTION && uiState.isSearching) {
                        IconButton(onClick = { viewModel.toggleSearch() }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Destinations.QUESTION.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Destinations.QUESTION.name) {
                val filteredItems = if (uiState.searchInput.isNotBlank())
                    uiState.items.filter {
                        val question = stringResource(it.question)
                        question.contains(uiState.searchInput, ignoreCase = true)
                    } else uiState.items
                QuestionsScreen(
                    items = filteredItems,
                    navigateToContent = {
                        navController.navigate(route = Destinations.ANSWER.name)
                        viewModel.updateSelectedItem(it)
                    }
                )
            }
            composable(route = Destinations.ANSWER.name) {
                AnswerScreen(
                    item = uiState.selectedItem,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.answer_screen_padding))
                        .verticalScroll(rememberScrollState())
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    Android101Theme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Android101App()
        }
    }
}