package com.example.newbees.ui.newsdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newbees.data.repository.BookmarkRepository
import com.example.newbees.data.sources.local.BookmarkNews
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val bookmarkNewsRepository: BookmarkRepository
):ViewModel() {

    fun insertNews(bookmarkNews: BookmarkNews){
        viewModelScope.launch {
            bookmarkNewsRepository.insertBookmarkNews(bookmarkNews)
        }
    }

}