package com.loodos.core.ui.pager

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlin.concurrent.fixedRateTimer

@Composable
fun PageIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorHeight: Dp = 8.dp,
    indicatorWidth: Dp = indicatorHeight,
    selectedColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
) {
    val color by rememberUpdatedState(newValue = selectedColor)
    assert(
        value = currentPage in 0 until totalPages,
        lazyMessage = { "Current page index is out of range." },
    )

    Column(modifier = modifier) {
        val totalWidth = (indicatorWidth * totalPages).coerceAtLeast(indicatorWidth * 3)
        Box(
            modifier = Modifier
                .width(totalWidth)
                .height(indicatorHeight)
                .align(Alignment.CenterHorizontally)
                .background(color.copy(alpha = .2f), shape = CircleShape),
        ) {
            val currentOffset = (currentPage * (totalWidth - indicatorWidth) / (totalPages - 1)).coerceAtMost(totalWidth - indicatorWidth)
            val offset by animateDpAsState(targetValue = currentOffset, animationSpec = tween(durationMillis = 200), label = "")
            Canvas(
                modifier = Modifier
                    .offset(x = offset)
                    .width(indicatorWidth)
                    .height(indicatorHeight),
                onDraw = {
                    drawRoundRect(
                        color = color,
                        cornerRadius = CornerRadius(indicatorHeight.toPx() / 2),
                        size = Size(indicatorWidth.toPx(), indicatorHeight.toPx()),
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun PreviewPageIndicator() {
    MaterialTheme {
        val totalPages = 10
        var currentPage by remember {
            mutableStateOf(0)
        }
        LaunchedEffect(Unit) {
            fixedRateTimer("pager_timer", period = 1000L) {
                if (currentPage == totalPages) {
                    currentPage = 0
                }
                currentPage++
            }
        }
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            PageIndicator(
                totalPages = totalPages,
                currentPage = currentPage,
                indicatorWidth = 8.dp,
                indicatorHeight = 3.dp,
            )
        }
    }
}
