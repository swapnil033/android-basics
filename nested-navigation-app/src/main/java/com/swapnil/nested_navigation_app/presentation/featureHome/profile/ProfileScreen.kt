package com.swapnil.nested_navigation_app.presentation.featureHome.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swapnil.nested_navigation_app.presentation.ui.Screens

@Composable
fun ProfileScreen(
    nextScreen: (Screens) -> Unit,
) {

    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { nextScreen(Screens.Home.Gallery) }) {
            Text(text = "Gallery")
        }
        Button(onClick = { nextScreen(Screens.Home.Setting) }) {
            Text(text = "Setting")
        }
    }
}