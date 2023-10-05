package com.swapnil.nested_navigation_app.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.swapnil.nested_navigation_app.presentation.featureAuth.forgetPass.ForgetPassScreen
import com.swapnil.nested_navigation_app.presentation.featureAuth.login.LoginScreen
import com.swapnil.nested_navigation_app.presentation.featureAuth.login.LoginViewModel
import com.swapnil.nested_navigation_app.presentation.featureAuth.registration.RegistrationScreen
import com.swapnil.nested_navigation_app.presentation.featureHome.gallery.GalleryScreen
import com.swapnil.nested_navigation_app.presentation.featureHome.profile.ProfileScreen
import com.swapnil.nested_navigation_app.presentation.featureHome.setting.SettingScreen
import com.swapnil.nested_navigation_app.presentation.featureSplash.SplashScreen
import com.swapnil.nested_navigation_app.presentation.ui.theme.IntentPracticeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentPracticeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation()
                }
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Splash.route,
        builder = {
            navigation(
                startDestination = Screens.Splash.Splash.route,
                route = Screens.Splash.route
            ){
                composable(route = Screens.Splash.Splash.route){
                    SplashScreen {
                        navController.navigate(Screens.Auth.route){
                            popUpTo(Screens.Splash.route)
                        }
                    }
                }
            }

            navigation(
                startDestination = Screens.Auth.Login.route,
                route = Screens.Auth.route
            ) {
                composable(route = Screens.Auth.Login.route) {
                    val loginViewModel = it.SharedViewModel<LoginViewModel>(navHostController = navController)
                    loginViewModel.setName("Swapnil")

                    LoginScreen(loginViewModel) {
                        navController.navigate(it.route){
                            if (it == Screens.Home){
                                popUpTo(Screens.Auth.route)
                            }
                        }
                    }
                }
                composable(route = Screens.Auth.ForgetPass.route){
                    val loginViewModel = it.SharedViewModel<LoginViewModel>(navHostController = navController)
                    ForgetPassScreen(loginViewModel)
                }
                composable(route = Screens.Auth.Register.route){
                    val loginViewModel = it.SharedViewModel<LoginViewModel>(navHostController = navController)
                    RegistrationScreen(loginViewModel)
                }
            }

            navigation(
                startDestination = Screens.Home.Profile.route,
                route = Screens.Home.route
            ) {
                composable(route = Screens.Home.Profile.route) {
                    ProfileScreen{ navController.navigate(it.route)}
                }
                composable(route = Screens.Home.Setting.route){
                    SettingScreen()
                }
                composable(route = Screens.Home.Gallery.route){
                    GalleryScreen()
                }
            }
        }
    )
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.SharedViewModel(navHostController: NavHostController): T {

    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navHostController.getBackStackEntry(navGraphRoute)
    }

    return viewModel(parentEntry)

}