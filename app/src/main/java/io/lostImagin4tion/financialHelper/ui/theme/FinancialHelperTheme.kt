package io.lostImagin4tion.financialHelper.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = finHelperPrimary,
    secondary = finHelperPrimaryVariant,
    primaryContainer = finHelperPrimaryButton,
    secondaryContainer = finHelperSecondaryButton,
    tertiaryContainer = finHelperTertiaryButton,
    surface = Color.Black,
    surfaceVariant = finHelperPrimaryButton,
    surfaceTint = finHelperDarkGray,
    inverseSurface = finHelperLightBackground,
    onSurface = Color.White,
    inverseOnSurface = Color.Black,
    onBackground = finHelperVeryDarkBackground,
    background = finHelperDarkBackground,
    error = finHelperRed,
    outline = Color(0xFFC9C9C9),
    scrim = finHelperChipColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = finHelperPrimaryVariant,
    secondary = finHelperPrimary,
    primaryContainer = finHelperPrimaryButton,
    secondaryContainer = finHelperSecondaryButton,
    tertiaryContainer = finHelperTertiaryButton,
    surface = Color.White,
    surfaceVariant = Color.Black,
    inverseSurface = finHelperDarkBackground,
    surfaceTint = finHelperLightBackground,
    onSurface = Color.Black,
    inverseOnSurface = Color.White,
    onBackground = finHelperLightBackground,
    background = finHelperLightBackground,
    error = finHelperRed,
    outline = finHelperTertiaryButton,
    scrim = finHelperChipColorLight
)

@Composable
fun FinancialHelperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}