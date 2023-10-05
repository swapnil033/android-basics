package com.swapnil.nested_navigation_app.presentation.featureAuth.registration

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.swapnil.nested_navigation_app.presentation.featureAuth.login.LoginViewModel

@Composable
fun RegistrationScreen(
    loginViewModel: LoginViewModel
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${loginViewModel.name.value} RegistrationScreen")
    }
}