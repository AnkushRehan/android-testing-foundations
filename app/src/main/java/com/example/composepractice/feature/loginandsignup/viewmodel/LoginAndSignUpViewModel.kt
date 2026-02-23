package com.example.composepractice.feature.loginandsignup.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composepractice.core.utils.UserData
import com.example.composepractice.domain.model.userInformation.ClientInformation
import com.example.composepractice.domain.repository.loginandsignup.LoginAndSignUpRepository
import com.example.composepractice.feature.loginandsignup.LoginUiState
import com.example.composepractice.feature.loginandsignup.LoginaAndSignUpEffect
import com.example.composeproject.data.models.MovieInformationRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginAndSignUpViewModel @Inject constructor(
    private val apiRepository: LoginAndSignUpRepository
) : ViewModel() {

    private val _response = MutableStateFlow<MovieInformationRoot?>(null)
    val response: StateFlow<MovieInformationRoot?> = _response.asStateFlow()

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            apiRepository.getMusicListWithFlow().collectLatest {
                _response.value = it
            }
        }
    }


//    fun onSignUpClicked(
//        userName: String,
//        password: String,
//        context: Context,
//        onSuccess: () -> Unit
//    ) {
//        if (userName.isNotEmpty() && password.isNotEmpty()) {
//            val info = ClientInformation().apply {
//                this.userName = userName
//                this.password = password
//            }
//
//            // Business Logic: Saving data
//            UserData.saveData(info, context)
//
//            // Tell the UI to navigate
//            onSuccess()
//        } else {
//            context.showToast("Please fill all details")
//        }
//    }

//    fun onSignInClicked(
//        userName: String,
//        password: String,
//        context: Context,
//
//        onSuccess: () -> Unit
//    ) {
//        if(userName.isNotEmpty() && password.isNotEmpty())
//        {
//            UserData.getSaveData(context).let {
//
//                if (it.userName != userName) context.showToast("invalid user name")
//                if (it.password != password) context.showToast("invalid password")
//                else onSuccess()
//
//
//            }
//
//        } else {
//            context.showToast("Please fill all details")
//        }
//    }




    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    private val _effect = MutableSharedFlow<LoginaAndSignUpEffect>()
    val effect: SharedFlow<LoginaAndSignUpEffect> = _effect

    fun onUserNameChange(value: String) {
        _uiState.update { it.copy(userName = value) }
    }

    fun onPasswordChange(value: String) {
        _uiState.update { it.copy(password = value) }
    }

    fun onSignUpClicked(context : Context)= viewModelScope.launch {

        val state = _uiState.value

        if (state.userName.isEmpty() || state.password.isEmpty()) {
            _effect.emit(LoginaAndSignUpEffect.ShowMessage("Please fill all details"))
            return@launch
        }

        UserData.saveData(ClientInformation().apply {
            this.userName = state.userName
            this.password = state.password
        } , context)

        _effect.emit(LoginaAndSignUpEffect.NavigateHome)

    }

    fun onSignInClicked(context : Context) = viewModelScope.launch {
        val state = _uiState.value

        if (state.userName.isEmpty() || state.password.isEmpty()) {
            _effect.emit(LoginaAndSignUpEffect.ShowMessage("Please fill all details"))
            return@launch
        }


        UserData.getSaveData(context).let {

                if (it.userName != state.userName)
                {
                    _effect.emit(LoginaAndSignUpEffect.ShowMessage("invalid user name"))
                    return@launch
                }
                if (it.password != state.password)
                {
                    _effect.emit(LoginaAndSignUpEffect.ShowMessage("invalid password"))
                    return@launch
                }


            }

        // do validation / repo call etc
        val ok = true // replace with real check

        if (ok) {
            _effect.emit(LoginaAndSignUpEffect.NavigateHome)
        } else {
            _effect.emit(LoginaAndSignUpEffect.ShowMessage("Invalid credentials"))
        }
    }

}
