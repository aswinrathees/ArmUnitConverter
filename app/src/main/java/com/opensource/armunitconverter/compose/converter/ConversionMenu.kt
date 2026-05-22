package com.opensource.armunitconverter.compose.converter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.opensource.armunitconverter.data.Conversion

@Composable
fun ConversionMenu(
    list: List<Conversion>,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
    convert: (Conversion) -> Unit
) {
    var displayingText by rememberSaveable { mutableStateOf("Select the conversion type") }
    // To assign the drop down the same width as TextField
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var expanded by remember { mutableStateOf(false) }

    // This is part of material theme [Not material3 as it is not bundled inside it and comes from material]
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        val mod = if (isLandscape) {
            modifier.onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.toSize()
            }
        } else {
            modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                }
        }
        OutlinedTextField(
            value = displayingText,
            onValueChange = { displayingText = it },
            textStyle = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = mod,
            label = { Text(text = "Conversion type") },
            trailingIcon = {
                Icon(icon, contentDescription = "icon", Modifier.clickable {
                    expanded = !expanded
                })
            },
            readOnly = true
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            })
        ) {
            list.forEach { conversion ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = conversion.description,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    onClick = {
                        displayingText = conversion.description
                        expanded = false
                        convert(conversion)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversionMenuPreview() {
    val list = listOf(
        Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        Conversion(2, "Kilograms to Pounds", "kg", "lbs", 2.20462),
        Conversion(3, "Yards to Meters", "yd", "m", 0.9144),
        Conversion(4, "Meters to Yards", "m", "yd", 1.09361)
    )
    ConversionMenu(list = list, isLandscape = false, convert = {})
}