package com.example.firstdlapp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.channels.FileChannel

class MainViewModel(context: Context) : ViewModel() {

    private val interpreter =
        InterpreterHelper.create(context, "model.tflite")

    var inputText by mutableStateOf("10")
    var outputText by mutableStateOf("")
        private set

    fun onPredictClick() {
        val inputValue = inputText.toFloatOrNull()
        outputText = if (inputValue != null) {
            val input = arrayOf(floatArrayOf(inputValue))
            val output = Array(1) { FloatArray(1) }
            interpreter.run(input, output)
            output[0][0].toString()
        } else {
            "Please enter a valid number"
        }
    }

    override fun onCleared() {
        interpreter.close()
        super.onCleared()
    }
}

object InterpreterHelper {
    fun create(context: Context, modelName: String): Interpreter {
        val fileDescriptor = context.assets.openFd(modelName)
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val channel = inputStream.channel
        val buffer = channel.map(
            FileChannel.MapMode.READ_ONLY,
            fileDescriptor.startOffset,
            fileDescriptor.declaredLength
        )
        return Interpreter(buffer)
    }
}