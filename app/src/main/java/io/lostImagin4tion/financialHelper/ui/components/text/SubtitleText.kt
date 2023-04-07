package io.lostImagin4tion.financialHelper.ui.components.text

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun SubtitleText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    textWeight: FontWeight = FontWeight.Bold,
    isLarge: Boolean = false
) = BaseSubtitleText(
    text = stringResource(textRes),
    modifier = modifier,
    color = color,
    textAlign = textAlign,
    textWeight = textWeight,
    isLarge = isLarge
)

@Composable
fun SubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    textWeight: FontWeight = FontWeight.Bold,
    isLarge: Boolean = false
) = BaseSubtitleText(
    text = text,
    modifier = modifier,
    color = color,
    textAlign = textAlign,
    textWeight = textWeight,
    isLarge = isLarge
)

@Composable
private fun BaseSubtitleText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
    textAlign: TextAlign = TextAlign.Start,
    textWeight: FontWeight = FontWeight.Bold,
    isLarge: Boolean = false
) = Text(
    text = text,
    style = if (isLarge) {
        MaterialTheme.typography.titleMedium.copy(fontWeight = textWeight)
    } else {
        MaterialTheme.typography.titleSmall.copy(fontWeight = textWeight)
    },
    modifier = modifier,
    color = color,
    textAlign = textAlign
)
