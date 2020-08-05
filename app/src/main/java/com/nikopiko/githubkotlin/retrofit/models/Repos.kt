package com.nikopiko.githubkotlin.retrofit.models

import com.google.gson.annotations.SerializedName

data class Repos(@SerializedName("total_count") val totalCount:Int,
                 @SerializedName("incomlete_results") val incompleteResults:Boolean,
                 val items:List<Items>) {

}