package com.sharath070.everydayjobalertui.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sharath070.everydayjobalertui.R
import com.sharath070.everydayjobalertui.adapters.JobFeedAdapter
import com.sharath070.everydayjobalertui.databinding.FragmentJobSearchBinding
import com.sharath070.everydayjobalertui.ui.activities.MainActivity
import com.sharath070.everydayjobalertui.utils.Resource
import com.sharath070.everydayjobalertui.viewModels.MainViewModel

class JobSearchFragment : Fragment() {

    private var _binding: FragmentJobSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobSearchBinding.inflate(layoutInflater, container, false)

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)

        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.showMenu){
                showPopupMenu()
            }
            true
        }

        return binding.root
    }


    private lateinit var viewModel: MainViewModel

    private lateinit var jobFeedAdapter: JobFeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel

        setupRecyclerView()
        showJobList()

        jobFeedAdapter.setOnItemClickListener {
            val direction = JobSearchFragmentDirections.actionJobSearchFragmentToWebViewFragment(it.link)
            findNavController().navigate(direction)
        }

    }

    private fun showJobList() {
        viewModel.jobFeed.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        jobFeedAdapter.submitList(it)
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error in Response", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.scrollView.visibility = View.VISIBLE
    }

    private fun showProgressBar() {
        binding.scrollView.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }


    private fun setupRecyclerView() {
        jobFeedAdapter = JobFeedAdapter()
        binding.rvJobList.apply {
            adapter = jobFeedAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showPopupMenu() {
        val popupMenu = PopupMenu(requireContext(), binding.view)
        popupMenu.inflate(R.menu.popup_menu)
        popupMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}