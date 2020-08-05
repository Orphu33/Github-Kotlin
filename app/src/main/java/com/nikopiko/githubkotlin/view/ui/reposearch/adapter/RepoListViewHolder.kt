package com.nikopiko.githubkotlin.view.ui.reposearch.adapter

import android.util.Log
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.nikopiko.githubkotlin.BR
import com.nikopiko.githubkotlin.R
import com.nikopiko.githubkotlin.retrofit.models.Items
import com.nikopiko.githubkotlin.view.RepoListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_repo_list_item.view.*

class RepoListViewHolder constructor(private val dataBinding: ViewDataBinding,
                                     private val repoListViewModel: RepoListViewModel
)
    : RecyclerView.ViewHolder(dataBinding.root) {

    val avatarImage = itemView.item_avatar
    fun setup(itemData: Items) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

       Picasso.get().load(itemData.owner.avatarUrl).into(avatarImage);

        itemView.item_avatar.setOnClickListener {
            Log.i("OWNER", itemData.owner.toString())
            repoListViewModel.setOwnerDetail(itemData.owner)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_userDetailFragment)
        }

        itemView.item_name.setOnClickListener {
            Log.i("REPO", itemData.toString())
            repoListViewModel.setRepoDetail(itemData)
            itemView.findNavController().navigate(R.id.action_repoListFragment_to_repoDetailFragment)
        }

    }

}