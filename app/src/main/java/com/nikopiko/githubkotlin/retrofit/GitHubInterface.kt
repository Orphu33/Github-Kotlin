package com.nikopiko.githubkotlin.retrofit

import com.nikopiko.githubkotlin.retrofit.models.Repos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubInterface {

    @GET("/search/repositories")
    fun searchRepos(@Query("q") query: String): Call<Repos>

    @GET("search/repositories")
    fun getTrending(@Query("q") search: String = "trending", @Query("sort") sort: String = "stars"): Call<Repos>
}