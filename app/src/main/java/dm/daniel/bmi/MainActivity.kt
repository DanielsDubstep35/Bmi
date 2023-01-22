package dm.daniel.bmi

import android.os.Bundle
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dm.daniel.bmi.ui.theme.BmiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiTheme {
        Bmi()
    }
}

@androidx.compose.runtime.Composable
fun Bmi() {
    var heightInput: String by remember { mutableStateOf( "") }
    var weightInput: String by remember { mutableStateOf( "") }
    var customfont = FontFamily(Font(R.font.montserrat_alternates_light_italic))
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi = if (weight > 0 && height > 0 ) weight / (height * height) else 0.0
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(
                listOf(Color.Cyan, Color.Blue, Color.Black),
                start = Offset(0.0f, 0.0f),
                end = Offset(2500.0f, 2500.0f)
            ))
    ) {
        Text(
            text = stringResource(R.string.body_mass_index),
            fontSize = 32.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontFamily = customfont,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = {heightInput = it.replace (',','.')},
            label = {Text (stringResource(R.string.height))},
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = {weightInput = it.replace (',','.')},
            label = {Text (stringResource(R.string.weight))},
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedLabelColor = Color.Black,
                focusedLabelColor = Color.Blue,
                unfocusedIndicatorColor = Color.Black,
                focusedIndicatorColor = Color.Blue,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Text(
            text = stringResource(R.string.result, String.format("%.2f", bmi).replace(',','.')),
            modifier = Modifier
                .padding(16.dp),
            color = Color.Black
        )
    }
}