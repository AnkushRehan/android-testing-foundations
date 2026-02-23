package com.example.composepractice.data.remote.api

import com.example.composeproject.data.models.MovieInformationRoot
import retrofit2.Response
import retrofit2.http.GET

interface WebService {


    @GET("horizon-code-academy/fake-movies-api/movies")
    suspend fun getMusic(): Response<MovieInformationRoot>
}