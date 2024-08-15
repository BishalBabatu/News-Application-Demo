package com.example.newbees.data.sources.remote.dto

import com.example.newbees.data.sources.local.NewsItems

data class News(
    val articles: List<NewsItems>,
    val status: String,
    val totalResults: Int
)