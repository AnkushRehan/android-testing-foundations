package com.example.composepractice.data.repository.loginandsignup

import com.example.composepractice.data.remote.api.WebService
import com.example.composepractice.domain.repository.loginandsignup.LoginAndSignUpRepository
import com.example.composeproject.data.models.MovieInformationRoot
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginAndSignUpRepositoryImpl @Inject constructor(
    private val webService: WebService
) : LoginAndSignUpRepository {

    override suspend fun getMusicListWithFlow(): Flow<MovieInformationRoot> {
        val response = webService.getMusic()
        return flow {
            emit(response.body()!!)
        }.catch { e ->
            // Handle error
        }
    }
}