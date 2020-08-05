package com.nikopiko.githubkotlin.view.ui.repodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.nikopiko.githubkotlin.BR
import com.nikopiko.githubkotlin.R
import com.nikopiko.githubkotlin.databinding.FragmentRepoDetailBinding
import com.nikopiko.githubkotlin.view.RepoListViewModel

class RepoDetailFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentRepoDetailBinding
    private val viewModel: RepoListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRepoDetailBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewDataBinding.setVariable(BR.item, viewModel.repoDetailLive.value)
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.repoDetailLive.value?.repoName

        viewDataBinding.repoName.setOnClickListener {
            viewModel.setWebViewUrl(viewModel.repoDetailLive.value?.htmlUrl!!)
            view.findNavController().navigate(R.id.action_repoDetailFragment_to_webViewFragment)
        }

        viewDataBinding.name.setOnClickListener {
            viewModel.setOwnerDetail(viewModel.repoDetailLive.value?.owner!!)
            view.findNavController().navigate(R.id.action_repoDetailFragment_to_userDetailFragment)
        }
    }
}