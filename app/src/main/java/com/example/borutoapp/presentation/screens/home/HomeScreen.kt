package com.example.borutoapp.presentation.screens.home

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil3.annotation.ExperimentalCoilApi
import com.example.borutoapp.presentation.common.ListContent

@OptIn(ExperimentalCoilApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

    Log.d("HomeScreen all heroes: ", allHeroes.loadState.toString())
    Scaffold(
        topBar = { HomeTopBar(onSearchClicked = {}) }
    ) { padding ->
        ListContent(
            heroes = allHeroes,
            navController = navController,
            padding = padding
        )
    }
}

