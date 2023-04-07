package io.lostImagin4tion.financialHelper.ui.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

@Composable
fun SimpleCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {}
) = Box(
    modifier = modifier
        .background(
            color = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(10)
        )
        .clip(RoundedCornerShape(10))
) {
    content()
}