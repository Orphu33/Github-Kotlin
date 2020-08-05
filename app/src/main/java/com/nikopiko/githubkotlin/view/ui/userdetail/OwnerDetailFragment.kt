package com.nikopiko.githubkotlin.view.ui.userdetail

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
import com.nikopiko.githubkotlin.databinding.FragmentOwnerDetailBinding
import com.nikopiko.githubkotlin.view.RepoListViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_owner_detail.*

class OwwerDetailFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentOwnerDetailBinding
    private val viewModel: RepoListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentOwnerDetailBinding.inflate(inflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewDataBinding.setVariable(BR.owner, viewModel.ownerDetailLive.value)
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.ownerDetailLive.value?.login

        Picasso.get().load(viewDataBinding.owner?.avatarUrl).into(avatar)

        viewDataBinding.avatar.setOnClickListener {
            viewModel.setWebViewUrl(viewModel.ownerDetailLive.value?.htmlUrl!!)
            view.findNavController().navigate(R.id.action_userDetailFragment_to_webViewFragment)
        }

    }
}