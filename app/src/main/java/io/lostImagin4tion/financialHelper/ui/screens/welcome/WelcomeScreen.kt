package io.lostImagin4tion.financialHelper.ui.screens.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.airbnb.lottie.RenderMode
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.navigation.Routes
import io.lostImagin4tion.financialHelper.domain.entities.ui.WelcomeScreenEntity
import io.lostImagin4tion.financialHelper.ui.components.buttons.TextFilledButton
import io.lostImagin4tion.financialHelper.ui.components.pager.PagerIndicator
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    navController: NavHostController
) {
    val welcomeScreenEntities = if (isSystemInDarkTheme()) {
        listOf(
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_track_money_subtitle,
                animationRes = R.raw.ic_anim_track_money_dark
            ),
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_set_goals_subtitle,
                animationRes = R.raw.ic_anim_goals_dark
            ),
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_charts_subtitle,
                animationRes = R.raw.ic_anim_chart_dark
            )
        )
    } else {
        listOf(
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_track_money_subtitle,
                animationRes = R.raw.ic_anim_track_money_light
            ),
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_set_goals_subtitle,
                animationRes = R.raw.ic_anim_goals_light
            ),
            WelcomeScreenEntity(
                subtitleRes = R.string.welcome_screen_charts_subtitle,
                animationRes = R.raw.ic_anim_chart_light
            )
        )
    }

    val navigateToHomeScreen: () -> Unit = {
        navController.navigate(Routes.home) {
            popUpTo(Routes.welcome) {
                inclusive = true
            }
        }
    }

    WelcomeScreenContent(
        welcomeScreenEntities = welcomeScreenEntities,
        navigateToHomeScreen = navigateToHomeScreen
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun WelcomeScreenContent(
    welcomeScreenEntities: List<WelcomeScreenEntity> = emptyList(),
    navigateToHomeScreen: () -> Unit = {}
) = Box(
    modifier = Modifier.fillMaxSize()
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    val pageCount = 3

    val navigateToNextPage: () -> Unit = {
        scope.launch {
            if (pagerState.currentPage < pageCount - 1) {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            } else {
                navigateToHomeScreen()
            }
        }
    }

    HorizontalPager(
        state = pagerState,
        pageCount = pageCount,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val animationComposition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                welcomeScreenEntities[page].animationRes
            )
        )

        val progress by animateLottieCompositionAsState(
            animationComposition,
            iterations = LottieConstants.IterateForever,
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

            LottieAnimation(
                composition = animationComposition,
                alignment = Alignment.Center,
                progress = { progress },
                renderMode = RenderMode.HARDWARE,
                maintainOriginalImageBounds = true,
                applyOpacityToLayers = true,
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 5))

            DisplayText(
                textRes = welcomeScreenEntities[page].subtitleRes,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = Dimensions.mainHorizontalPadding)
                    .weight(1f)
            )
        }
    }

    Column(
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = Dimensions.mainVerticalPadding * 2)
    ) {
        PagerIndicator(
            pageCount = pageCount,
            pagerState = pagerState,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

        TextFilledButton(
            onClick = navigateToNextPage,
            textResource = if (pagerState.currentPage < pageCount - 1) {
                R.string.welcome_screen_button_next
            } else {
                R.string.welcome_screen_button_lets_start
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimensions.mainHorizontalPadding)
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF1F1F1)
private fun WelcomeScreenPreview() = FinancialHelperTheme {
    WelcomeScreenContent()
}