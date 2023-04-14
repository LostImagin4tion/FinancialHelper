package io.lostImagin4tion.financialHelper.ui.screens.financialGoals

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.navigation.Routes
import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity
import io.lostImagin4tion.financialHelper.ui.components.cards.SimpleCard
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.components.text.LabelText
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray

@Composable
fun FinancialGoalsScreen(
    navController: NavHostController
) {
    val viewModel: FinancialGoalsViewModel = viewModel()

    val financialGoals = viewModel.financialGoalsResult.collectAsState()

    LaunchedEffect(key1 = financialGoals) {
        viewModel.getAllGoals()
    }

    val navigateToNewFinancialGoal = { navController.navigate(Routes.newFinancialGoal) }

    FinancialGoalsScreenContent(
        financialGoals = financialGoals.value.data ?: emptyList(),
        navigateToNewGoalScreen = navigateToNewFinancialGoal,
        navigateBack = navController::popBackStack,
    )
}

@Composable
fun FinancialGoalsScreenContent(
    financialGoals: List<FinancialGoalEntity> = emptyList(),
    navigateToNewGoalScreen: () -> Unit = {},
    navigateBack: () -> Unit = {}
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToNewGoalScreen,
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
        FinancialGoalsScreenMainContent(
            navigateBack = navigateBack,
            financialGoals = financialGoals,
            paddingValues = it,
        )
    }
}

@Composable
private fun FinancialGoalsScreenMainContent(
    navigateBack: () -> Unit = {},
    financialGoals: List<FinancialGoalEntity> = emptyList(),
    paddingValues: PaddingValues = PaddingValues(0.dp),
) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
        .padding(paddingValues)
) {
    val lazyColumnState = rememberLazyListState()

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = navigateBack,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.commonPadding))

        DisplayText(
            textRes = R.string.financial_goals_screen_title
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    LazyColumn(
        state = lazyColumnState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        items(financialGoals) { goal ->
            var isExpanded by rememberSaveable {
                mutableStateOf(false)
            }

            SimpleCard(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimensions.commonPadding)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SubtitleText(text = goal.title)

                        Spacer(modifier = Modifier.weight(1f))

                        if (goal.description.isNotBlank()) {
                            IconButton(
                                onClick = { isExpanded = !isExpanded },
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(
                                        color = finHelperGray,
                                        shape = CircleShape
                                    )
                                    .clip(CircleShape)
                            ) {
                                Icon(
                                    painter = if (isExpanded) {
                                        painterResource(R.drawable.ic_arrow_up)
                                    } else {
                                        painterResource(R.drawable.ic_arrow_down)
                                    },
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(Dimensions.commonPadding))

                        LabelText(text = goal.targetDate)
                    }

                    Spacer(modifier = Modifier.height(Dimensions.commonPadding))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_dollar),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(24.dp)
                        )

                        Spacer(modifier = Modifier.width(Dimensions.commonPadding))

                        LabelText(
                            text = goal.sumToAchieve
                                .toString()
                                .format("%.2f")
                        )
                    }

                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = expandVertically(animationSpec = tween(300)),
                        exit = shrinkVertically(animationSpec = tween(300))
                    ) {
                        if (isExpanded) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

                                LabelText(
                                    text = goal.description,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(Dimensions.commonPadding))
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF1F1F1)
private fun HomeScreenPreview() = FinancialHelperTheme {
    FinancialGoalsScreenMainContent()
}
