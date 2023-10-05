package com.swapnil.nested_navigation_app.presentation.featureAuth.login

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
fun LoginScreen(
    loginViewModel: LoginViewModel,
    nextScreen: (Screens) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = loginViewModel.name.value)


        Button(onClick = { nextScreen(Screens.Auth.ForgetPass) }) {
            Text(text = "Forget pass")
        }
        Button(onClick = { nextScreen(Screens.Auth.Register) }) {
            Text(text = "Register")
        }
        Button(onClick = { nextScreen(Screens.Home) }) {
            Text(text = "Login")
        }
    }
}