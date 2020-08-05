package com.nikopiko.githubkotlin.view.ui.reposearch

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikopiko.githubkotlin.databinding.FragmentRepoListBinding
import com.nikopiko.githubkotlin.view.RepoListViewModel
import com.nikopiko.githubkotlin.view.ui.reposearch.adapter.RepoListAdapter
import kotlinx.android.synthetic.main.fragment_repo_list.*
import org.jetbrains.anko.longToast

class RepoListFragment : Fragment(){

    private lateinit var viewDataBinding: FragmentRepoListBinding
    private val viewModel: RepoListViewModel by activityViewModels()
    private lateinit var adapter: RepoListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentRepoListBinding.inflate(inflater, container, false).apply {
            viewmodel = ViewModelProviders.of(this@RepoListFragment).get(RepoListViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObservers()

        viewDataBinding.searchButton.setOnClickListener {
            it.hideKeyboard()
            val queryString = viewDataBinding.searchText.text.toString()
            viewDataBinding.viewmodel?.searchRepoList(queryString)
        }

    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.repoListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
        })

        viewDataBinding.viewmodel?.toastMessage?.observe(viewLifecycleOwner, Observer {
            activity?.longToast(it)
        })
    }

    private fun setupAdapter() {
        adapter = RepoListAdapter(viewModel)
        val layoutManager = LinearLayoutManager(activity)
        repo_list_rv.layoutManager = layoutManager
        repo_list_rv.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
        repo_list_rv.adapter = adapter

    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}