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
import androidx.compose.ui.unit.sp

@Composable
fun SmallLabelText(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.primary,
    isLarge: Boolean = false
) = BaseSmallLabelText(
    text = stringResource(textRes),
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
fun SmallLabelText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.primary,
    isLarge: Boolean = false
) = BaseSmallLabelText(
    text = text,
    modifier = modifier,
    textAlign = textAlign,
    textColor = textColor,
    isLarge = isLarge
)

@Composable
private fun BaseSmallLabelText(
    text: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Start,
    textColor: Color = MaterialTheme.colorScheme.primary,
    isLarge: Boolean = false
) = Text(
    text = text,
    fontWeight = if (isLarge) FontWeight.SemiBold else FontWeight.Medium,
    fontSize = if (isLarge) 12.sp else 14.sp,
    lineHeight = 14.sp,
    modifier = modifier,
    textAlign = textAlign,
    color = textColor
)
