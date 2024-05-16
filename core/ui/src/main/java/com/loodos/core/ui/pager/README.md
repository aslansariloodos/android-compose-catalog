## Preview

![img.png](img.png)

## Usage

```kotlin
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
```