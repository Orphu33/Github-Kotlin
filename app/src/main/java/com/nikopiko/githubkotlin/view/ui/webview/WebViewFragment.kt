package com.nikopiko.githubkotlin.view.ui.webview

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.nikopiko.githubkotlin.R
import com.nikopiko.githubkotlin.view.RepoListViewModel
import kotlinx.android.synthetic.main.fragment_web_view.*


class WebViewFragment : Fragment() {

    private val viewModel: RepoListViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = viewModel.webViewUrl.value

        web_view.loadUrl(url)

        setupWebView()
        setClickListeners()


    }

    private fun setClickListeners() {
        repo_back_button.setOnClickListener {
            web_view.goBack()
        }

        repo_forward_button.setOnClickListener {
            web_view.goForward()
        }

        repo_refresh_button.setOnClickListener {
            web_view.reload()
        }
    }

    private fun setupWebView() {
        web_view.setInitialScale(1)
        val webSettings = web_view.settings
        webSettings.setAppCacheEnabled(false)
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = false
        webSettings.javaScriptEnabled = true
        webSettings.useWideViewPort = true
        webSettings.domStorageEnabled = true

        web_view.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                if (repo_back_button != null && repo_forward_button != null && web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = web_view.canGoBack()
                    repo_forward_button.isEnabled = web_view.canGoForward()
                    repo_progress_view.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (repo_back_button != null && repo_forward_button != null && web_view != null && repo_progress_view != null) {
                    repo_back_button.isEnabled = web_view.canGoBack()
                    repo_forward_button.isEnabled = web_view.canGoForward()
                    repo_progress_view.visibility = View.GONE
                }
            }
        }
    }
}