package com.opensource.armunitconverter.compose.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.opensource.armunitconverter.data.Conversion
import java.math.RoundingMode
import java.text.DecimalFormat

@Composable
fun TopScreen(
    list: List<Conversion>,
    selectedConversion: MutableState<Conversion?>,
    inputState: MutableState<String>,
    typedValue: MutableState<String>,
    isLandscape: Boolean,
    save: (String, String) -> Unit
) {
    var toSave by remember { mutableStateOf(false) }

    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        ConversionMenu(list, isLandscape) {
            selectedConversion.value = it
            typedValue.value = "0.0"
        }

        selectedConversion.value?.let {
            InputBlock(it, inputState, isLandscape) { input ->
                typedValue.value = input
                toSave = true
            }
        }

        if (typedValue.value != "0.0") {
            val input = typedValue.value.toDouble()
            val multiply = selectedConversion.value!!.multiplyBy
            val result = input * multiply

            // Rounding off the result to 4 decimal places
            val df = DecimalFormat("#.####")
            df.roundingMode = RoundingMode.DOWN
            val finalResult = df.format(result)

            val message1 =
                "${typedValue.value} ${selectedConversion.value!!.convertFrom} is equal to"
            val message2 = "$finalResult ${selectedConversion.value!!.convertTO}"
            if (toSave) {
                save(message1, message2)
                toSave = false
            }
            ResultBlock(message1 = message1, message2 = message2)
        }
    }
}

@Preview
@Composable
fun TopScreenPreview() {
    val list = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361)
    )
    TopScreen(
        list,
        remember { mutableStateOf(Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592)) },
        remember { mutableStateOf("") },
        remember { mutableStateOf("0.0") },
        false
    ) { _, _ -> }
}