package com.versity.EService

import AuthChooseScreen
import HomePageClient
import LoginScreen
import OnboardingScreen
import SignUpScreen
import SplashScreen
import VerificationCodeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.runtime.Composable

import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()}
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Splash") {
        composable("Splash") { SplashScreen(navController) }
        composable("onBoarding") { OnboardingScreen(navController) }
        composable("chooseAuth") { AuthChooseScreen(navController) }
        composable("loginScreen") { LoginScreen(navController) }
        composable("signUpScreen") { SignUpScreen(navController) }
        composable("otpInputScreen") { VerificationCodeScreen(navController) }
        composable("homePageScreenClient") { HomePageClient(navController) }}}