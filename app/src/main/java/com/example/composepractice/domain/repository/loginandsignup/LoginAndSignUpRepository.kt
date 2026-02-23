package com.example.composepractice.domain.repository.loginandsignup

import com.example.composeproject.data.models.MovieInformationRoot
import kotlinx.coroutines.flow.Flow

interface LoginAndSignUpRepository {

    suspend fun getMusicListWithFlow(): Flow<MovieInformationRoot>
}