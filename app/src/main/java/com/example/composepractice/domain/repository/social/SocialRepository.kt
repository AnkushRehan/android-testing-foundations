package com.example.composepractice.domain.repository.social

import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRoot
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem
import com.example.composepractice.domain.utils.responseUtil.Resource
import kotlinx.coroutines.flow.Flow

interface SocialRepository {
    suspend fun getSocialPosts(): Flow<Resource<SocialPostInformationRoot>>
    suspend fun savePost(post: SocialPostInformationRootItem)
    fun getLocalPosts(): Flow<List<SocialPostInformationRootItem>>
}