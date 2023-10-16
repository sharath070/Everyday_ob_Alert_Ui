package com.sharath070.everydayjobalertui.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.sharath070.everydayjobalertui.R
import com.sharath070.everydayjobalertui.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment() {

    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWebViewBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private val args: WebViewFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.link)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}