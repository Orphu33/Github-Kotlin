package com.nikopiko.githubkotlin.view.ui.reposearch

import com.nikopiko.githubkotlin.retrofit.APIClient
import com.nikopiko.githubkotlin.retrofit.models.Repos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepoRepository {

    fun getTrendingList(onResult: (isSuccess: Boolean, response: Repos?) -> Unit) {

        APIClient.gitHubApi.getTrending().enqueue(object : Callback<Repos> {
            override fun onResponse(call: Call<Repos>?, response: Response<Repos>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<Repos>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    fun searchRepoList(query: String, onResult: (isSuccess: Boolean, response: Repos?) -> Unit) {

        APIClient.gitHubApi.searchRepos(query).enqueue(object : Callback<Repos> {
            override fun onResponse(call: Call<Repos>?, response: Response<Repos>?) {
                if (response != null && response.isSuccessful)
                    onResult(true, response.body()!!)
                else
                    onResult(false, null)
            }

            override fun onFailure(call: Call<Repos>?, t: Throwable?) {
                onResult(false, null)
            }

        })
    }

    companion object {
        private var INSTANCE: RepoRepository? = null
        fun getInstance() = INSTANCE
            ?: RepoRepository().also {
                INSTANCE = it
            }
    }
}