package com.sharath070.everydayjobalertui.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sharath070.everydayjobalertui.R
import com.sharath070.everydayjobalertui.adapters.JobFeedAdapter
import com.sharath070.everydayjobalertui.databinding.FragmentJobSearchBinding
import com.sharath070.everydayjobalertui.repository.Repository
import com.sharath070.everydayjobalertui.utils.Resource
import com.sharath070.everydayjobalertui.viewModels.MainViewModel
import com.sharath070.everydayjobalertui.viewModels.MainViewModelFactory

class JobSearchFragment : Fragment() {

    private var _binding: FragmentJobSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    private lateinit var viewModel: MainViewModel

    private lateinit var jobFeedAdapter: JobFeedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), viewModelFactory)[MainViewModel::class.java]

        setupRecyclerView()
        showJobList()



    }

    private fun showJobList() {
        viewModel.jobFeed.observe(viewLifecycleOwner) { response ->

            when (response) {
                is Resource.Success -> {
                    //hideProgressBar()
                    response.data?.let {
                        jobFeedAdapter.submitList(it)
                    }
                }

                is Resource.Error -> {
                    Toast.makeText(requireContext(), "Error in Response", Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                    //showProgressBar()
                }
            }
        }
    }


    private fun setupRecyclerView() {
        jobFeedAdapter = JobFeedAdapter()
        binding.rvJobList.apply {
            adapter = jobFeedAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}