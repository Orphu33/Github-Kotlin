package com.nikopiko.githubkotlin.view.ui.reposearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nikopiko.githubkotlin.databinding.FragmentRepoListItemBinding
import com.nikopiko.githubkotlin.retrofit.models.Items
import com.nikopiko.githubkotlin.view.RepoListViewModel

class RepoListAdapter(private val repoListViewModel: RepoListViewModel) : RecyclerView.Adapter<RepoListViewHolder>() {
    var repoList: List<Items> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = FragmentRepoListItemBinding.inflate(inflater, parent, false)
        return RepoListViewHolder(
            dataBinding,
            repoListViewModel
        )
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    fun updateRepoList(repoList: List<Items>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}