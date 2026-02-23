package com.example.composepractice.feature.loginandsignup

data class LoginUiState(
    val userName: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
