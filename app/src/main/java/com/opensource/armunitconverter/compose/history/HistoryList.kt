package com.opensource.armunitconverter.compose.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opensource.armunitconverter.data.ConversionResult

@Composable
fun HistoryList(
    list: List<ConversionResult>,
    onCloseTask: (ConversionResult) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = list,
            key = { item -> item.id }
        ) { item ->
            HistoryItem(
                messagePart1 = item.messagePart1,
                messagePart2 = item.messagePart2,
                onClose = { onCloseTask(item) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryListPreview() {
    val sampleList = listOf(
        ConversionResult(1, "10 kg", "is equal to 22.05 lbs"),
        ConversionResult(2, "5 meters", "is equal to 16.40 feet"),
        ConversionResult(3, "100 Celsius", "is equal to 212.00 Fahrenheit")
    )
    HistoryList(
        list = sampleList,
        onCloseTask = {}
    )
}