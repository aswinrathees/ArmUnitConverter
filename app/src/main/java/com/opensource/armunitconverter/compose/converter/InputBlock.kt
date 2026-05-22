package com.opensource.armunitconverter.compose.converter

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.opensource.armunitconverter.data.Conversion

@Composable
fun InputBlock(
    conversion: Conversion,
    inputText: MutableState<String>,
    isLandscape: Boolean,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    calculate: (String) -> Unit
) {
    var textFieldModifier: Modifier = Modifier
    var textModifier: Modifier = Modifier

    if (!isLandscape) {
        textFieldModifier = Modifier.fillMaxWidth(0.65F)
        textModifier = Modifier.fillMaxWidth(0.35F)
    }

    Column(modifier = modifier.padding(0.dp, 20.dp, 0.dp, 0.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            TextField(
                value = inputText.value,
                onValueChange = {
                    inputText.value = it
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrectEnabled = true,
                    keyboardType = KeyboardType.Number
                ),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F),
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3F)
                ),
                textStyle = TextStyle(color = Color.DarkGray, fontSize = 30.sp),
                modifier = textFieldModifier
            )
            Text(
                text = conversion.convertFrom,
                fontSize = 24.sp,
                modifier = textModifier.padding(10.dp, 30.dp, 0.dp, 0.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(
            onClick = {
                if (inputText.value.isNotEmpty()) {
                    calculate(inputText.value)
                } else {
                    Toast.makeText(context, "Please enter your value", Toast.LENGTH_LONG).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Convert",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputBlockPreview() {
    InputBlock(
        conversion = Conversion(1, "Pounds to Kilograms", "lbs", "kg", 0.453592),
        inputText = remember { mutableStateOf("10") },
        isLandscape = true,
        context = LocalContext.current,
        calculate = {}
    )
}