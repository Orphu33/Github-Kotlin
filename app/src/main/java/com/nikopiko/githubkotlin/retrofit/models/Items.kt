package com.nikopiko.githubkotlin.retrofit.models

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.gson.annotations.SerializedName
import java.util.*

data class Items(
    @SerializedName("id") val repoId:Int,
    @SerializedName("name") val repoName: String,
    @SerializedName("full_name") val fullName: String,
    @SerializedName("private") val isPrivate: Boolean,
    val owner: Owner,
    @SerializedName("html_url") val htmlUrl: String,
    val description: String,
    @SerializedName("stargazers_count") val stargazersCount: Int,
    @SerializedName("watchers_count") val watchersCount: Int,
    @SerializedName("has_issues") val hasIssues: Boolean,
    @SerializedName("forks_count") val forksCount: Int,
    @SerializedName("open_issues_count") val openIssuesCount: Int,
    val forks: Int,
    @SerializedName("open_issues") val openIssues: Int,
    val watchers: Int,
    val homepage: String,
    val language: String,
    @SerializedName("pushed_at") val pushedAt: Date,
    @SerializedName("created_at") val createdAt: Date,
    @SerializedName("updated_at") val updatedAt: Date
    ) {

    override fun toString(): String {
        return "Items(repoId=$repoId, repoName='$repoName', fullName='$fullName', owner=$owner, htmlUrl='$htmlUrl', description='$description', stargazersCount=$stargazersCount, watchersCount=$watchersCount, forksCount=$forksCount, openIssuesCount=$openIssuesCount, watchers=$watchers, homepage='$homepage', language='$language', updatedAt=$updatedAt)"
    }
}

@BindingAdapter("android:updated")
fun bindUpdated(view: TextView, dateText:Date) {
    view.setText(String.format(Locale.getDefault(), "%tB %tY", dateText, dateText))
}
