package com.mobjoy.newsappmobjoyv1.model

import com.mobjoy.newsappmobjoyv1.model.apis.sources.SourcesResponse
import com.mobjoy.newsappmobjoyv1.model.articles.ArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    //coroutines -> suspend

    @GET("v2/top-headlines/sources")
    suspend fun getSource(
        @Query("apiKey") apiKey: String = Constansts.API_KEY,
        @Query("category") category: String = "general"
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNews(
        @Query("apiKey") apiKey: String = Constansts.API_KEY,
        @Query("q") query:String ="Microsoft"
    ):ArticlesResponse


    @GET("v2/everything")
    suspend fun searchFromNews(
        @Query("apiKey") apiKey: String = Constansts.API_KEY,
        @Query("q") query:String
    ):ArticlesResponse




}