package com.swapnil.nested_navigation_app.presentation.ui

sealed class Screens(val route: String){
    object Splash: Screens("splash"){
        object Splash: Screens("splashScreen")
    }

    object Auth: Screens("auth"){
        object Login: Screens("login")
        object Register: Screens("register")
        object ForgetPass: Screens("forgetPass")
    }

    object Home: Screens("home"){
        object Profile: Screens("profile")
        object Setting: Screens("setting")
        object Gallery: Screens("gallery")
    }
}
