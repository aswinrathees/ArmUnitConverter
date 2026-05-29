package com.opensource.armunitconverter.compose.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.opensource.armunitconverter.data.ConversionResult

@Composable
fun HistoryScreen(list: List<ConversionResult>,
                  onCloseTask: (ConversionResult) -> Unit,
                  onClearAllTask: () -> Unit,
                  modifier: Modifier) {
    Column {
        if (list.isNotEmpty()) {
            Row(modifier = modifier.fillMaxWidth().padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "History", color = Color.Gray)
                OutlinedButton(onClick = { onClearAllTask }) {
                    Text(text = "Clear All", color = Color.Gray)
                }
            }
        }
    }

    HistoryList(list = list, onCloseTask = { item ->
        onCloseTask(item)
    })
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    val sampleList = listOf(
        ConversionResult(1, "10 kg", "is equal to 22.05 lbs"),
        ConversionResult(2, "5 meters", "is equal to 16.40 feet"),
        ConversionResult(3, "100 Celsius", "is equal to 212.00 Fahrenheit")
    )
    HistoryScreen(
        list = sampleList,
        onCloseTask = {},
        onClearAllTask = {},
        modifier = Modifier
    )
}