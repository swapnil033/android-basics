package com.swapnil.intentpracticeapp

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.swapnil.intentpracticeapp.ui.theme.IntentPracticeAppTheme

class MainActivity : ComponentActivity() {

    val viewModel by viewModels<ImageViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(Intent.EXTRA_STREAM)) {
            val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
            } else {
                intent?.getParcelableExtra(Intent.EXTRA_STREAM)
            }
            viewModel.updateImageUri(uri)
        }

        setContent {
            IntentPracticeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Body(
                        viewModel.imageUri.value,
                        this
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }

        if (uri == null){
            Log.d("Intent-TAG", "onNewIntent: null")
        }

        viewModel.updateImageUri(uri)
    }
}

@Composable
fun Body(
    imageUri: Uri?,
    context: Context,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        imageUri?.let {
            AsyncImage(model = it, contentDescription = null)
        }

        CustomButton(
            text = "Open 2nd activity",
            onClick = {
                Intent(context, SecondActivity::class.java).also {
                    context.startActivity(it)
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            text = "Open youtube",
            onClick = {
                Intent(Intent.ACTION_MAIN).apply {
                    this.`package` = "com.google.android.youtube"
                    try {
                        context.startActivity(this)
                    }catch (e: ActivityNotFoundException){
                        e.printStackTrace()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomButton(
            text = "Open email",
            onClick = {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "This is the subject")
                    putExtra(Intent.EXTRA_TEXT, "This is email body text")
                }

                if (intent.resolveActivity(context.packageManager) != null)
                    context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

    }
}

@Composable
fun CustomButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = text)
    }
}