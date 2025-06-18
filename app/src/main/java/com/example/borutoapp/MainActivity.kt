package com.example.borutoapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.borutoapp.domain.use_cases.UseCases
import com.example.borutoapp.navigation.Screen
import com.example.borutoapp.navigation.SetUpNavGraph
import com.example.borutoapp.ui.theme.BorutoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @Inject
    lateinit var useCases: UseCases
    val completed = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeOnBoardingState()
        installSplashScreen()
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.light(
                Color.TRANSPARENT,
                Color.TRANSPARENT
            )
        )
        setContent {
            BorutoAppTheme {
                navController = rememberNavController()
                SetUpNavGraph(
                    navController = navController,
                    startDestination = if (completed.value) Screen.Home.route
                    else Screen.Welcome.route
                )
            }
        }
    }

    private fun observeOnBoardingState() {
        lifecycleScope.launch(Dispatchers.IO) {
            useCases.readOnBoardingUseCase.invoke().collect {
                completed.value = it
            }
        }
    }
}
