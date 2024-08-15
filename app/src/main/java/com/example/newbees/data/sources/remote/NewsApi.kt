package com.example.newbees.data.sources.remote

import com.example.newbees.data.sources.remote.dto.News
import com.example.newbees.data.sources.remote.dto.Source
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("v2/top-headlines")
    suspend fun getGeneralNews(
        @Query("page") page:Int,
        @Query("country") country:String,
    ): News


    @GET("/v2/everything")
    suspend fun getNewsByQuery(
        @Query("page") page: Int,
        @Query("q") queryString:String,
        @Query("language") language:String="en"
    ):News


    @GET("v2/top-headlines")
    suspend fun getTopCategoriesHeadlines(
        @Query("page") page:Int,
        @Query("category") category:String,
        @Query("country") country:String,
    ):News


    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("language") language:String
    ): Source


}