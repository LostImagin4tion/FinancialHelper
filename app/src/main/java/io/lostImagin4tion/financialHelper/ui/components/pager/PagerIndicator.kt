package io.lostImagin4tion.financialHelper.ui.components.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerIndicator(
    pageCount: Int,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) = Row(
    horizontalArrangement = Arrangement.Start,
    verticalAlignment = Alignment.Top,
    modifier = modifier
) {
    repeat(pageCount) { i ->
        val color = if (pagerState.currentPage == i) {
            MaterialTheme.colorScheme.primary
        } else {
            finHelperGray
        }

        Box(
            modifier = Modifier
                .padding(Dimensions.commonPadding / 2)
                .clip(CircleShape)
                .background(color)
                .size(10.dp)
        )
    }
}
