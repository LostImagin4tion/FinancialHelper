package io.lostImagin4tion.financialHelper.ui.screens.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.lostImagin4tion.financialHelper.domain.entities.navigation.Routes
import io.lostImagin4tion.financialHelper.ui.screens.expenses.ExpensesScreen
import io.lostImagin4tion.financialHelper.ui.screens.financialGoals.FinancialGoalsScreen
import io.lostImagin4tion.financialHelper.ui.screens.home.HomeScreen
import io.lostImagin4tion.financialHelper.ui.screens.income.IncomeScreen
import io.lostImagin4tion.financialHelper.ui.screens.newFinancialGoal.NewFinancialGoalScreen
import io.lostImagin4tion.financialHelper.ui.screens.newItem.NewItemScreen
import io.lostImagin4tion.financialHelper.ui.screens.welcome.WelcomeScreen

@Composable
fun Navigation(
    paddingValues: PaddingValues,
    navController: NavHostController,
    isFirstLaunch: Boolean = true,
    changeIsFirstLaunch: () -> Unit = {}
) {
    NavigationContent(
        paddingValues = paddingValues,
        navController = navController,
        isFirstLaunch = isFirstLaunch,
        changeIsFirstLaunch = changeIsFirstLaunch
    )
}

@Suppress("MagicNumber")
@Composable
fun NavigationContent(
    paddingValues: PaddingValues,
    navController: NavHostController,
    isFirstLaunch: Boolean = true,
    changeIsFirstLaunch: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = if (isFirstLaunch) {
                changeIsFirstLaunch()
                remember { Routes.welcome }
            } else {
                remember { Routes.home }
            }
        ) {
            composable(Routes.welcome) {
                WelcomeScreen(
                    navController = navController
                )
            }

            composable(route = Routes.home) {
                HomeScreen(
                    navController = navController
                )
            }

            composable(route = Routes.newItem) {
                NewItemScreen(
                    navController = navController
                )
            }

            composable(route = Routes.financialGoals) {
                FinancialGoalsScreen(
                    navController = navController
                )
            }

            composable(route = Routes.newFinancialGoal) {
                NewFinancialGoalScreen(
                    navController = navController
                )
            }

            composable(route = Routes.income) {
                IncomeScreen(
                    navController = navController
                )
            }

            composable(route = Routes.expenses) {
                ExpensesScreen(
                    navController = navController
                )
            }
        }
    }
}
