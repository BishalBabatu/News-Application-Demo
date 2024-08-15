package com.example.newbees.data.repository

import com.example.newbees.data.sources.local.BookmarkNews
import kotlinx.coroutines.flow.Flow


interface BookmarkRepository {
    suspend fun insertBookmarkNews(bookmarkNews: BookmarkNews)

    fun getAllBookmarkedNews(): Flow<List<BookmarkNews>>

    suspend fun deleteBookmarkedNews(bookmarkNews: BookmarkNews)
}