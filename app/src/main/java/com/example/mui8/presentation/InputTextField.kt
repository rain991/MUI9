package com.example.mui8.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mui8.data.InputError

@Composable
fun InputTextField(modifier : Modifier) {
    var text by remember { mutableStateOf("") }
    var errors by remember { mutableStateOf<List<InputError>>(emptyList()) }

    var blockUppercase by remember { mutableStateOf(false) }
    var blockLowercase by remember { mutableStateOf(false) }
    var blockNonAlphanumeric by remember { mutableStateOf(false) }
    var blockDigits by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            value = text,
            shape = RoundedCornerShape(16.dp),
            onValueChange = { newText ->
                val newErrors = mutableListOf<InputError>()

                if (blockUppercase && newText.any { it.isUpperCase() }) {
                    newErrors.add(InputError.UppercaseNotAllowed)
                }

                if (blockLowercase && newText.any { it.isLowerCase() }) {
                    newErrors.add(InputError.LowercaseNotAllowed)
                }

                if (blockNonAlphanumeric && newText.any { !it.isLetterOrDigit() }) {
                    newErrors.add(InputError.NonAlphanumericNotAllowed)
                }

                if (blockDigits && newText.any { it.isDigit() }) {
                    newErrors.add(InputError.DigitsNotAllowed)
                }

                if (newErrors.isEmpty()) {
                    text = newText
                }
                errors = newErrors
            },
            label = { Text("Wprowadź tekst") },
            isError = errors.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(14.dp))
        errors.forEach {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                Text(
                    text = it.message,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 2.dp),
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Blokuj wielkie litery")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = blockUppercase, onCheckedChange = { blockUppercase = it })
        }

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Blokuj małe litery")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = blockLowercase, onCheckedChange = { blockLowercase = it })
        }

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Blokuj znaki niealfanumeryczne")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = blockNonAlphanumeric, onCheckedChange = { blockNonAlphanumeric = it })
        }

        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Blokuj cyfry")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(checked = blockDigits, onCheckedChange = { blockDigits = it })
        }
    }
}