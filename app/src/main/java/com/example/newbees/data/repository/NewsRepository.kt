package com.example.newbees.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newbees.data.sources.local.NewsDatabase
import com.example.newbees.data.sources.local.NewsItems
import com.example.newbees.data.sources.remote.NewsApi
import com.example.newbees.data.sources.remote.dto.SourceItem
import com.example.newbees.data.sources.remote.paging.TrendingNewsRemoteMediator
import com.example.newbees.data.sources.remote.paging.pagingsource.NewsPagingSource
import com.example.newbees.data.sources.remote.paging.pagingsource.SearchNewsPagingSource
import com.example.newbees.data.sources.remote.paging.pagingsource.SourcesPaging
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NewsRepository
@Inject constructor(
    private val newsApi: NewsApi,
    private val database: NewsDatabase
){

    // Trending News fragment has remote mediator feature
    @OptIn(ExperimentalPagingApi::class)
    val trendingNewsPagedData = Pager(
        config = PagingConfig(
            20,
            maxSize = 150,
            initialLoadSize = 100
        ), pagingSourceFactory = {database.getNewsDao.getAllNews()},
        remoteMediator = TrendingNewsRemoteMediator(newsApi,database)
    ).flow

    fun newsQueryPager(queryText:String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 150,
            initialLoadSize = 100),
        pagingSourceFactory = { SearchNewsPagingSource(newsApi,queryText) }
    ).flow

    fun getCategoryNews(category: String):Flow<PagingData<NewsItems>>{
        return  Pager(
            config = PagingConfig(
                20,
                maxSize = 150,
                initialLoadSize = 100
            ), pagingSourceFactory = { NewsPagingSource(newsApi,category) }
        ).flow
    }

    fun getNewsSources():Flow<PagingData<SourceItem>>{
        return  Pager(
            config = PagingConfig(
                20,
                maxSize = 150,
                initialLoadSize = 100
            ), pagingSourceFactory = { SourcesPaging(newsApi) }
        ).flow
    }

}