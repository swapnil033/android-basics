package com.swapnil.uritypeapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.net.toUri
import com.swapnil.uritypeapp.ui.theme.IntentPracticeAppTheme
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val resourceUri = Uri.parse("android.resource://$packageName/drawable/maruchan_the_cat")

        val maruChanImg = contentResolver.openInputStream(resourceUri)?.use {
            it.readBytes()
        }
        Log.d("Main-TAG", "onCreate: ${maruChanImg?.size}")

        val file = File(filesDir, "maruChanTheCat.jpg")
        FileOutputStream(file).use {
            it.write(maruChanImg)
        }
        val fileUri = file.toUri()
        Log.d("Main-TAG", "onCreate: $fileUri")

        val dataUri = Uri.parse("data:text/plain;charset=UTF-8,Hello%20World")

        Log.d("Main-TAG", "onCreate: $dataUri")

        setContent {
            IntentPracticeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pickImage = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.GetContent(),
                        onResult = {contentUri ->
                            Log.d("Main-TAG", "onCreate: $contentUri")
                        }
                    )

                    Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = { pickImage.launch("image/*") }) {
                            Text(text = "Pick image")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IntentPracticeAppTheme {
        Greeting("Android")
    }
}