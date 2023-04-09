package io.lostImagin4tion.financialHelper.ui.components.buttons

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions

@Composable
fun CustomTextButton(
    @StringRes textResource: Int,
    colors: ButtonColors,
    modifier: Modifier = Modifier,
    @DrawableRes leadingIconRes: Int? = null,
    onTrailingIconCLick: () -> Unit = {},
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    contentPadding: PaddingValues = PaddingValues(),
    onClick: () -> Unit = {}
) = TextButton(
    onClick = onClick,
    colors = colors,
    contentPadding = contentPadding,
    modifier = modifier
        .defaultMinSize(
            minWidth = 1.dp,
            minHeight = 1.dp
        )
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        leadingIconRes?.let {
            IconButton(
                onClick = onTrailingIconCLick,
                modifier = Modifier
                    .size(20.dp)
                    .padding(start = 4.dp),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(leadingIconRes),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.width(Dimensions.commonPadding))
        }

        Text(
            text = stringResource(textResource),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = fontSize,
                fontWeight = fontWeight
            ),
        )
    }
}
