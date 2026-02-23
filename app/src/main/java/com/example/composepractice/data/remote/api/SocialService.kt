package com.example.composepractice.data.remote.api

import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRoot
import retrofit2.Response
import retrofit2.http.GET

interface SocialService {


    @GET("posts")
    suspend fun getSocialPosts (): Response<SocialPostInformationRoot>
}