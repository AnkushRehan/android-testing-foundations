package com.example.composepractice.feature.loginandsignup

sealed class LoginaAndSignUpEffect {
    data class ShowMessage(val message: String) : LoginaAndSignUpEffect()
    data object NavigateHome : LoginaAndSignUpEffect() // or: object NavigateHome : LoginEffect()
}