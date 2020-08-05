package com.nikopiko.githubkotlin.retrofit.models

import com.google.gson.annotations.SerializedName

data class Owner (
    val login: String,
    @SerializedName("id") val ownerId: Int,
    @SerializedName("avatar_url") val avatarUrl: String,
    val url: String,
    @SerializedName("html_url") val htmlUrl: String
    //@SerializedName("url") val reposUrl: String
)