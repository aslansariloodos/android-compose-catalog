@file:OptIn(ExperimentalFoundationApi::class)

package com.loodos.catalog

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.loodos.core.ui.pager.PageIndicator

@Composable
fun Catalog(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        item {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "PageIndicator")
                val list = (0..4).toList()
                val pagerState = rememberPagerState { list.size }
                HorizontalPager(state = pagerState) {
                    Surface(
                        modifier = Modifier.padding(16.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(2f)
                        )
                    }
                }
                Spacer(modifier = Modifier.size(4.dp))
                PageIndicator(
                    totalPages = list.size,
                    currentPage = pagerState.currentPage,
                    indicatorWidth = 8.dp,
                    indicatorHeight = 3.dp,
                )
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
        item { HorizontalDivider() }
    }
}