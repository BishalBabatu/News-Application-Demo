package com.example.newbees.data.sources.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.newbees.data.sources.local.BookmarkNews
import kotlinx.coroutines.flow.Flow


@Dao
interface BookmarkNewsDao {

    @Upsert
    suspend fun insertNews(bookmarkNews: BookmarkNews)

    @Query("SELECT * FROM BookmarkNews")
    fun getAllBookmarkedNews(): Flow<List<BookmarkNews>>

    @Query("Delete from BookmarkNews")
    suspend fun deleteAllBookmarkedNews()

    @Delete
    suspend fun deleteBookmarkedNews(bookmarkNews: BookmarkNews)


}