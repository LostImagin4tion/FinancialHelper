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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.navigation.Routes
import io.lostImagin4tion.financialHelper.ui.components.cards.SimpleCard
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    val navigateToNewItemScreen = { navController.navigate(Routes.newItem) }

    HomeScreenContent(
        navigateToNewItemScreen = navigateToNewItemScreen
    )
}

@Composable
private fun HomeScreenContent(
    navigateToNewItemScreen: () -> Unit = {}
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
            paddingValues = it
        )
    }
}

@Composable
private fun HomeScreenMainContent(
    paddingValues: PaddingValues = PaddingValues(0.dp)
) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
        .padding(paddingValues)
) {

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .clickable { }
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

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        SimpleCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .weight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.commonPadding)
            ) {
                SubtitleText(textRes = R.string.home_screen_expenses_subtitle)
            }
        }

        Spacer(modifier = Modifier.width(Dimensions.mainHorizontalPadding / 2))

        SimpleCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .weight(1f)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimensions.commonPadding)
            ) {
                SubtitleText(textRes = R.string.home_screen_income_subtitle)
            }
        }
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF1F1F1)
private fun HomeScreenPreview() = FinancialHelperTheme {
    HomeScreenMainContent()
}