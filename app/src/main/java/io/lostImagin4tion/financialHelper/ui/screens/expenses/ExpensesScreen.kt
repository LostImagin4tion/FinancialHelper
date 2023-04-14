package io.lostImagin4tion.financialHelper.ui.screens.expenses

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.components.text.LabelText
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray

@Composable
fun ExpensesScreen(
    navController: NavHostController
) {
    val viewModel: ExpensesViewModel = viewModel()

    val expensesItems = viewModel.expensesResult.collectAsState()

    LaunchedEffect(key1 = expensesItems) {
        viewModel.getAllIncome()
    }

    ExpensesScreenContent(
        expensesItems = expensesItems.value.data ?: emptyList(),
        navigateBack = navController::popBackStack
    )
}

@Composable
private fun ExpensesScreenContent(
    expensesItems: List<ExpensesEntity> = emptyList(),
    navigateBack: () -> Unit = {}
) = Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
) {
    val iconBackgroundSize = 48.dp
    val iconSize = 32.dp

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
            textRes = R.string.expenses_screen_title
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    LazyColumn(
        state = lazyColumnState,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxSize()
    ) {
        items(expensesItems) { expenses ->
            var isExpanded by rememberSaveable {
                mutableStateOf(false)
            }

            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = Dimensions.mainVerticalPadding)
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = expenses.iconBackgroundColor,
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .size(iconBackgroundSize)
                ) {
                    Icon(
                        painter = painterResource(expenses.iconRes),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .size(iconSize)
                            .align(Alignment.Center)
                    )
                }

                Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding / 2))

                Column(
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.Start
                        ) {
                            SubtitleText(text = expenses.title)

                            Spacer(modifier = Modifier.height(Dimensions.commonPadding / 2))

                            LabelText(text = expenses.date)

                            Spacer(modifier = Modifier.height(Dimensions.commonPadding))
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.End
                        ) {
                            LabelText(
                                text = stringResource(R.string.expenses_screen_item_money)
                                    .format(expenses.sum)
                            )

                            if (expenses.description.isNotBlank()) {
                                Spacer(modifier = Modifier.height(Dimensions.commonPadding))

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
                                Spacer(modifier = Modifier.height(Dimensions.commonPadding))

                                LabelText(
                                    text = expenses.description,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF9F9F9)
private fun ExpensesScreenPreview() = FinancialHelperTheme {
    ExpensesScreenContent()
}
