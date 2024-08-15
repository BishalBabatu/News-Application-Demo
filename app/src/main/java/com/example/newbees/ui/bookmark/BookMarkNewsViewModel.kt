package com.example.newbees.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.newbees.data.repository.BookmarkRepository
import com.example.newbees.data.sources.local.BookmarkNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookMarkNewsViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
):ViewModel() {

    fun getAllNews(): Flow<List<BookmarkNews>> {
        return bookmarkRepository.getAllBookmarkedNews().catch { emit(emptyList()) }
    }

    val isBookmarkedArticleEmpty: LiveData<Boolean> = getAllNews().map { it.isEmpty() }.asLiveData()
    fun deleteBookmarkedNote(bookmarkNews: BookmarkNews){
        viewModelScope.launch {
            bookmarkRepository.deleteBookmarkedNews(bookmarkNews)
        }
    }
}