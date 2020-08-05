package com.nikopiko.githubkotlin.view

import androidx.lifecycle.MutableLiveData
import com.nikopiko.githubkotlin.retrofit.models.Items
import com.nikopiko.githubkotlin.retrofit.models.Owner
import com.nikopiko.githubkotlin.view.ui.reposearch.RepoRepository

enum class SORT {STARS, FORKS, UPDATED}

class RepoListViewModel : BaseViewModel() {
    val repoListLive = MutableLiveData<List<Items>>()
    val repoDetailLive = MutableLiveData<Items>()
    val ownerDetailLive = MutableLiveData<Owner>()
    val webViewUrl = MutableLiveData<String>()

    fun fetchTrendingList() {
        dataLoading.value = true
        RepoRepository.getInstance()
            .getTrendingList { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }


    }

    fun searchRepoList(query: String) {
        dataLoading.value = true
        RepoRepository.getInstance()
            .searchRepoList(query) { isSuccess, response ->
            dataLoading.value = false
            if (isSuccess) {
                repoListLive.value = response?.items
                empty.value = false
            } else {
                empty.value = true
            }
        }
    }

    fun setRepoDetail(items: Items) {
        repoDetailLive.value = items
    }

    fun setOwnerDetail(owner: Owner) {
        ownerDetailLive.value = owner
    }

    fun setWebViewUrl(url: String) {
        webViewUrl.value = url
    }

    fun sortRepo(sort: SORT) {
        var items = repoListLive.value!!
        items = when(sort) {
            SORT.FORKS -> items.sortedByDescending { it.forksCount }
            SORT.STARS -> items.sortedByDescending { it.stargazersCount }
            SORT.UPDATED -> items.sortedByDescending { it.updatedAt }
        }
        repoListLive.value = items
    }

}