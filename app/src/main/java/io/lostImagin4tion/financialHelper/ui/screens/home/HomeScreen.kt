package io.lostImagin4tion.financialHelper.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.patrykandpatrick.vico.core.extension.sumByFloat
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.navigation.Routes
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesLineChartData
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesPieChartData
import io.lostImagin4tion.financialHelper.ui.components.cards.SimpleCard
import io.lostImagin4tion.financialHelper.ui.components.charts.CustomLineChart
import io.lostImagin4tion.financialHelper.ui.components.charts.CustomPieChart
import io.lostImagin4tion.financialHelper.ui.components.text.LabelText
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val viewModel: HomeViewModel = viewModel()

    val navigateToFinancialGoals = { navController.navigate(Routes.financialGoals) }
    val navigateToNewItemScreen = { navController.navigate(Routes.newItem) }
    val navigateToIncomeScreen = { navController.navigate(Routes.income) }
    val navigateToExpensesScreen = { navController.navigate(Routes.expenses) }

    val lineChartData = viewModel.incomeAndExpensesChartResult.collectAsState()
    val pieChartData = viewModel.pieChartDataResult.collectAsState()

    LaunchedEffect(
        key1 = lineChartData,
        key2 = pieChartData
    ) {
        viewModel.getAllIncomeAndExpenses()
        viewModel.getIncomeAndExpensesForPieChart()
    }

    HomeScreenContent(
        incomeAndExpensesData = lineChartData.value.data,
        pieChartData = pieChartData.value.data,
        navigateToFinancialGoals = navigateToFinancialGoals,
        navigateToNewItemScreen = navigateToNewItemScreen,
        navigateToIncomeScreen = navigateToIncomeScreen,
        navigateToExpensesScreen = navigateToExpensesScreen
    )
}

@Composable
private fun HomeScreenContent(
    incomeAndExpensesData: IncomeAndExpensesLineChartData? = null,
    pieChartData: IncomeAndExpensesPieChartData? = null,
    navigateToFinancialGoals: () -> Unit = {},
    navigateToNewItemScreen: () -> Unit = {},
    navigateToIncomeScreen: () -> Unit = {},
    navigateToExpensesScreen: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNewItemScreen,
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.White,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_add),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        HomeScreenMainContent(
            incomeAndExpensesData = incomeAndExpensesData,
            pieChartData = pieChartData,
            navigateToFinancialGoals = navigateToFinancialGoals,
            navigateToIncomeScreen = navigateToIncomeScreen,
            navigateToExpensesScreen = navigateToExpensesScreen,
            paddingValues = it
        )
    }
}

@Composable
private fun HomeScreenMainContent(
    incomeAndExpensesData: IncomeAndExpensesLineChartData? = null,
    pieChartData: IncomeAndExpensesPieChartData? = null,
    navigateToFinancialGoals: () -> Unit = {},
    navigateToIncomeScreen: () -> Unit = {},
    navigateToExpensesScreen: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(0.dp)
) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
        .padding(paddingValues)
        .verticalScroll(rememberScrollState())
) {
    val incomeSum = incomeAndExpensesData?.incomeData
        ?.points
        ?.sumByFloat { it.sum }
        ?.toString()
        ?.format("%.2f")

    val expensesSum = incomeAndExpensesData?.expensesData
        ?.points
        ?.sumByFloat { it.sum }
        ?.toString()
        ?.format("%.2f")

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .clickable { navigateToFinancialGoals() }
            .padding(Dimensions.commonPadding)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = finHelperGray,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .size(36.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding / 2))

        SubtitleText(
            textRes = R.string.home_screen_goals,
            isLarge = true
        )

        Spacer(modifier = Modifier.width(Dimensions.commonPadding / 2))

        Icon(
            painter = painterResource(R.drawable.ic_arrow_right),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(20.dp)
        )
    }

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        SimpleCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { navigateToExpensesScreen() }
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.commonPadding)
            ) {
                SubtitleText(textRes = R.string.home_screen_expenses_subtitle)

                Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

                if (expensesSum == null) {
                    LabelText(
                        textRes = R.string.home_screen_statistics_no_data,
                        isLarge = true
                    )
                } else {
                    LabelText(
                        text = expensesSum,
                        isLarge = true
                    )

                    LabelText(textRes = R.string.income_or_expenses_sum)
                }

                Spacer(modifier = Modifier.height(Dimensions.commonPadding))
            }
        }

        Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding / 2))

        SimpleCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable { navigateToIncomeScreen() }
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.commonPadding)
            ) {
                SubtitleText(textRes = R.string.home_screen_income_subtitle)

                Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

                if (incomeSum == null) {
                    LabelText(
                        textRes = R.string.home_screen_statistics_no_data,
                        isLarge = true
                    )
                } else {
                    LabelText(
                        text = incomeSum,
                        isLarge = true
                    )

                    LabelText(textRes = R.string.income_or_expenses_sum)
                }

                Spacer(modifier = Modifier.height(Dimensions.commonPadding))
            }
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .clickable { navigateToFinancialGoals() }
            .padding(Dimensions.commonPadding)
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = finHelperGray,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .size(36.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_chart),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding / 2))

        SubtitleText(
            textRes = R.string.home_screen_statistics,
            isLarge = true
        )
    }

    if (incomeAndExpensesData == null || pieChartData == null ||
        (incomeAndExpensesData.expensesData.points.isEmpty() &&
         incomeAndExpensesData.incomeData.points.isEmpty()
        )
    ) {
        SimpleCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.commonPadding)
                    .height(150.dp)
            ) {
                Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

                SubtitleText(textRes = R.string.home_screen_statistics_no_data,)

                Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))
            }
        }
    } else {
        SimpleCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomLineChart(
                lineModel = incomeAndExpensesData.incomeData,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.commonPadding)
            )
        }

        Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

        SimpleCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomLineChart(
                lineModel = incomeAndExpensesData.expensesData,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimensions.commonPadding)
            )
        }

        Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

        SimpleCard(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.commonPadding)
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    CustomPieChart(
                        data = pieChartData.incomeData,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(Dimensions.commonPadding)
                    )

                    Spacer(modifier = Modifier.height(Dimensions.commonPadding))

                    pieChartData.incomeData.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        color = it.color,
                                        shape = CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(Dimensions.commonPadding / 2))

                            LabelText(textRes = it.typeRes)
                        }
                    }
                }

                Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding))

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(1f)
                ) {
                    CustomPieChart(
                        data = pieChartData.expensesData,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(Dimensions.commonPadding)
                    )

                    Spacer(modifier = Modifier.height(Dimensions.commonPadding))

                    pieChartData.expensesData.forEach {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.align(Alignment.Start)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .background(
                                        color = it.color,
                                        shape = CircleShape
                                    )
                            )

                            Spacer(modifier = Modifier.width(Dimensions.commonPadding / 2))

                            LabelText(textRes = it.typeRes)
                        }
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF1F1F1)
private fun HomeScreenPreview() = FinancialHelperTheme {
    HomeScreenMainContent()
}