package com.example.newbees.data.repository

import com.example.newbees.data.sources.local.BookmarkNews
import com.example.newbees.data.sources.local.NewsDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class BookmarkRepositoryImpl @Inject constructor(
    private val newsDatabase: NewsDatabase
):BookmarkRepository {
    override suspend fun insertBookmarkNews(bookmarkNews: BookmarkNews) {
        newsDatabase.getBookmarkedNewsDao.insertNews(bookmarkNews)
    }

    override fun getAllBookmarkedNews(): Flow<List<BookmarkNews>> {
        return newsDatabase.getBookmarkedNewsDao.getAllBookmarkedNews()
    }

    override suspend fun deleteBookmarkedNews(bookmarkNews: BookmarkNews) {
        newsDatabase.getBookmarkedNewsDao.deleteBookmarkedNews(bookmarkNews)
    }
}