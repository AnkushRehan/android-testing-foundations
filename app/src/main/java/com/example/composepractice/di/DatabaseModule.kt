package com.example.composepractice.di

import android.content.Context
import androidx.room.Room
import com.example.composepractice.data.local.dao.PostDao
import com.example.composepractice.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "social_db"
        ).build()
    }

    @Provides
    fun providePostDao(db: AppDatabase): PostDao = db.postDao()
}