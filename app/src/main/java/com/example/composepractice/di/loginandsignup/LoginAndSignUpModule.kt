package com.example.composepractice.di.loginandsignup

import com.example.composepractice.data.repository.loginandsignup.LoginAndSignUpRepositoryImpl
import com.example.composepractice.domain.repository.loginandsignup.LoginAndSignUpRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginAndSignUpModule {

    @Binds
    @Singleton
    abstract fun bindLoginAndSignUpRepository(
        loginAndSignUpRepositoryImpl: LoginAndSignUpRepositoryImpl
    ): LoginAndSignUpRepository
}