package com.example.composepractice.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composepractice.core.utils.UserData
import com.example.composepractice.feature.loginandsignup.LoginScreen
import com.example.composepractice.feature.loginandsignup.SignUpScreen
import com.example.composepractice.feature.social.DetailScreen
import com.example.composepractice.feature.social.ShowPostDetail


@Composable
fun AppNavigationGraph()
{

    val context = LocalContext.current

    val navigation = rememberNavController()


    NavHost(navigation, startDestination = Routes.TEST)
    {

        composable(route = Routes.TEST)
        {

            if( UserData.checkDataSession(context)) SignUpScreen(navController = navigation)
            else LoginScreen(navController = navigation)

        }

//        composable(route = Routes.TEST)
//        {
//
//            Test()
//
//        }


        composable(route = Routes.LOGIN_SCREEN)
        {

            LoginScreen(navController = navigation)

        }

        composable(route = Routes.SIGNUP_SCREEN)
        {

            SignUpScreen(navController = navigation)

        }

        composable(route = Routes.DETAIL_SCREEN)
        {

            DetailScreen(navController = navigation)

        }

        composable(route = Routes.SOCIAL_DETAIL_SCREEN)
        {

            ShowPostDetail(navController = navigation)

        }


    }

}