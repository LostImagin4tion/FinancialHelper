package io.lostImagin4tion.financialHelper.ui.screens.main

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.domain.dataStore.IDataStorage
import io.lostImagin4tion.financialHelper.ui.screens.navigation.Navigation
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController

    @Inject
    lateinit var dataStorage: IDataStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        FinancialHelperApp.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        installSplashScreen()

        val changeIsFirstLaunch: () -> Unit = {
            dataStorage.setIsFirstLaunch(false)
        }

        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            navController = rememberNavController()

            FinancialHelperTheme {
                SetupSystemBarsColors()

                Scaffold(
                    content = {
                        Navigation(
                            paddingValues = it,
                            navController = navController,
                            isFirstLaunch = dataStorage.isFirstLaunch,
                            changeIsFirstLaunch = changeIsFirstLaunch
                        )
                    }
                )
            }
        }
    }

    @Composable
    private fun SetupSystemBarsColors() {
        val view = LocalView.current

        if (!view.isInEditMode) {
            val currentWindow = (view.context as? Activity)?.window
                ?: error("Not in an Activity - unable to get Window reference")

            val navBarColor = MaterialTheme.colorScheme.background

            val statusBarColor = MaterialTheme.colorScheme.background

            val isLightStatusBar = !isSystemInDarkTheme()

            SideEffect {
                currentWindow.statusBarColor = statusBarColor.toArgb()
                currentWindow.navigationBarColor = navBarColor.toArgb()

                WindowCompat.getInsetsController(currentWindow, view)
                    .isAppearanceLightStatusBars = isLightStatusBar
            }
        }
    }
}
