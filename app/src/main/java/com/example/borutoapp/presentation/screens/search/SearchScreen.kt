package com.example.borutoapp.presentation.screens.search

import androidx.activity.compose.LocalActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.toArgb
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.common.ListContent
import com.example.borutoapp.ui.theme.statusBarColor
import com.example.borutoapp.ui.theme.topAppBarBackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val activity = LocalActivity.current
    val searchQuery by searchViewModel.searchQuery
    val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()
    val systemBarColor = statusBarColor.toArgb()

    SideEffect { activity?.window?.statusBarColor = systemBarColor }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    SearchTopBar(
                        text = searchQuery,
                        onTextChange = {
                            searchViewModel.updateSearchQuery(query = it)
                        },
                        onSearchClicked = {
                            searchViewModel.searchHeroes(query = it)
                        },
                        onCloseClicked = {
                            navController.popBackStack()
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = topAppBarBackgroundColor)
            )

        },
        content = { padding ->
            ListContent(
                padding = padding,
                heroes = heroes,
                navController = navController
            )
        }
    )
}