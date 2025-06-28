package com.example.firstdlapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstdlapp.ui.theme.FirstDLAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            val viewModel = remember {
                MainViewModel(applicationContext)
            }

            FirstDLAppTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(WindowInsets.systemBars.asPaddingValues())
                ) {
                    MainScreenPage(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreenPage(
    viewModel: MainViewModel
) {
    MainScreen(
        inputText = viewModel.inputText,
        outputText = viewModel.outputText,
        onInputChanged = { viewModel.inputText = it },
        onPredictClick = { viewModel.onPredictClick() }
    )
}

@Composable
fun MainScreen(
    inputText: String,
    outputText: String,
    onInputChanged: (String) -> Unit,
    onPredictClick: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = inputText,
            onValueChange = onInputChanged,
            label = { Text("Enter a number") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onPredictClick) {
            Text("Predict Run")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Predict Result: $outputText")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen(
        inputText = "10",
        outputText = "output",
        onInputChanged = {},
        onPredictClick = {})
}