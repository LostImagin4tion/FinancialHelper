package io.lostImagin4tion.financialHelper.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.ui.components.cards.SimpleCard
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    HomeScreenContent()
}

@Composable
fun HomeScreenContent(

) = Column(
    horizontalAlignment = Alignment.Start,
    verticalArrangement = Arrangement.Top,
    modifier = Modifier
        .fillMaxSize()
        .imePadding()
        .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
) {

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    DisplayText(
        textRes = R.string.home_screen_title
    )

    Spacer(modifier = Modifier.height(Dimensions.commonPadding))

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
    HomeScreenContent()
}