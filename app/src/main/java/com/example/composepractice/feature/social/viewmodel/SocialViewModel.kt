package com.example.composepractice.feature.social.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRoot
import com.example.composepractice.domain.model.socialInformation.SocialPostInformationRootItem
import com.example.composepractice.domain.repository.social.SocialRepository
import com.example.composepractice.domain.utils.responseUtil.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SocialViewModel @Inject constructor(
    private val repository: SocialRepository
) : ViewModel() {

    private val _response = MutableStateFlow<Resource<SocialPostInformationRoot>?>(null)
    val response: StateFlow<Resource<SocialPostInformationRoot>?> = _response.asStateFlow()

    private val _localPosts = MutableStateFlow<List<SocialPostInformationRootItem>>(emptyList())
    val localPosts: StateFlow<List<SocialPostInformationRootItem>> = _localPosts.asStateFlow()

    init {
        getMovies()
        observeLocalPosts()
    }

    private fun getMovies() {
        viewModelScope.launch {
            repository.getSocialPosts().collectLatest {
                _response.value = it
            }
        }
    }

    fun savePost(post: SocialPostInformationRootItem) {
        viewModelScope.launch {
            repository.savePost(post)
        }
    }

    private fun observeLocalPosts() {
        viewModelScope.launch {
            repository.getLocalPosts().collectLatest {
                _localPosts.value = it
            }
        }
    }
}