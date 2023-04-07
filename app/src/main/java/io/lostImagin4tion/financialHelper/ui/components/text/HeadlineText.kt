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
fun HeadlineText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    isLarge: Boolean = false
) = BaseHeadlineText(
    text = stringResource(textRes),
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
fun HeadlineText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    isLarge: Boolean = false
) = BaseHeadlineText(
    text = text,
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
private fun BaseHeadlineText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    isLarge: Boolean = false
) = Text(
    text = text,
    style = if (isLarge) {
        MaterialTheme.typography.headlineMedium
    } else {
        MaterialTheme.typography.headlineSmall
    },
    modifier = modifier,
    textAlign = textAlign,
    color = textColor
)
