package com.master.masteradaptaterbleutooth.core.routes

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.master.masteradaptaterbleutooth.auth.presentation.login.LoginScreen
import com.master.masteradaptaterbleutooth.auth.presentation.register.RegisterScreen
import com.master.masteradaptaterbleutooth.intro.presentation.IntroScreen

@Composable
fun Navigation(navC: NavHostController){
    NavHost(navController = navC, startDestination = ScreenRoute.Intro.name, route = "root") {
        composable(ScreenRoute.Intro.name) {
            IntroScreen(navC)
        }
        composable(ScreenRoute.Login.name) {
            LoginScreen(navC)
        }
        composable(ScreenRoute.Register.name) {
            RegisterScreen(navC)
        }
    }
}