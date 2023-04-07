package io.lostImagin4tion.financialHelper.ui.components.text

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign

@Composable
fun DisplayText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    isLarge: Boolean = false
) = BaseDisplayText(
    text = stringResource(textRes),
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
fun DisplayText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    isLarge: Boolean = false
) = BaseDisplayText(
    text = text,
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
private fun BaseDisplayText(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    isLarge: Boolean = false
) = Text(
    text = text,
    style = if (isLarge) {
        MaterialTheme.typography.displayLarge
    } else {
        MaterialTheme.typography.displayMedium
    },
    modifier = modifier,
    textAlign = textAlign,
    color = textColor
)
