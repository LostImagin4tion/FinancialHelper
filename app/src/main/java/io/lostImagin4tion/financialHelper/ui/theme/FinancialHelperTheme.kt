package io.lostImagin4tion.financialHelper.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = finHelperPrimary,
    secondary = finHelperPrimaryVariant,
    primaryContainer = finHelperPrimaryButton,
    secondaryContainer = finHelperSecondaryButton,
    tertiaryContainer = finHelperTertiaryButton,
    surface = finHelperCardColorDark,
    background = finHelperDarkBackground,
)

private val LightColorScheme = lightColorScheme(
    primary = finHelperPrimaryVariant,
    secondary = finHelperPrimary,
    primaryContainer = finHelperPrimaryButton,
    secondaryContainer = finHelperSecondaryButton,
    tertiaryContainer = finHelperTertiaryButton,
    surface = finHelperCardColorLight,
    background = finHelperLightBackground,
)

@Composable
fun FinancialHelperTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
