package com.example.newbees.di

import android.content.Context
import androidx.room.Room
import com.example.newbees.data.repository.BookmarkRepository
import com.example.newbees.data.repository.BookmarkRepositoryImpl
import com.example.newbees.data.sources.local.NewsDatabase
import com.example.newbees.utils.AppConstants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context = context,
            NewsDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideBookmarkRepositoryImpl(newsDatabase: NewsDatabase): BookmarkRepository {
        return BookmarkRepositoryImpl(newsDatabase)
    }

}