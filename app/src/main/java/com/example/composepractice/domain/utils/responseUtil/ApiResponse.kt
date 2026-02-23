package com.example.composeproject.data.networkSection.responseUtil

data class ApiResponse<out T>(
    val message: String? = null,
    val data: T? = null
)