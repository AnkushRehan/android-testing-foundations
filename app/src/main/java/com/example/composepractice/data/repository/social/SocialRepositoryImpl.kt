package com.example.composepractice.data.repository.social

import com.example.composepractice.data.local.dao.PostDao
import com.example.composepractice.data.mapper.toDomain
import com.example.composepractice.data.mapper.toEntity
import com.example.composepractice.data.remote.api.SocialService
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRoot
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem
import com.example.composepractice.domain.repository.social.SocialRepository
import com.example.composepractice.domain.utils.responseUtil.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SocialRepositoryImpl @Inject constructor(
    private val socialService: SocialService,
    private val postDao: PostDao
) : SocialRepository {

    override suspend fun getSocialPosts(): Flow<Resource<SocialPostInformationRoot>> = flow {

        emit(Resource.loading())

        val response = socialService.getSocialPosts()

        if (response.isSuccessful && response.body() != null) {
            emit(Resource.success(response.body()))
        } else {
//            emit(Resource.error(AppError("Server Error: ${response.code()}")))
        }
    }.catch { e ->

//        emit(Resource.error(AppError(e.localizedMessage ?: "Unknown Error")))
    }

    override suspend fun savePost(post: SocialPostInformationRootItem) {
        postDao.insertPost(post.toEntity())
    }


    override fun getLocalPosts(): Flow<List<SocialPostInformationRootItem>> {
        return postDao.getAllPosts().map { entities ->
            entities.map { it.toDomain() }
        }
    }
}