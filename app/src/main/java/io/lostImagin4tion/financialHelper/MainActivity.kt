package io.lostImagin4tion.financialHelper

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import io.lostImagin4tion.financialHelper.ui.screens.navigation.Navigation
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }

            FinancialHelperTheme {
                SetupSystemBarsColors()

                Scaffold(
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            modifier = Modifier.navigationBarsPadding()
                        ) {
                            Snackbar(
                                snackbarData = it,
                                containerColor = MaterialTheme.colorScheme.inverseSurface,
                                contentColor = MaterialTheme.colorScheme.inverseOnSurface,
                                shape = MaterialTheme.shapes.small
                            )
                        }
                    },
                    content = {
                        Navigation(
                            snackbarHostState = snackbarHostState,
                            paddingValues = it,
                            navController = navController
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
