package com.sharath070.everydayjobalertui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sharath070.everydayjobalertui.model.jobSearchModel.JobFeed
import com.sharath070.everydayjobalertui.repository.Repository
import com.sharath070.everydayjobalertui.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    init {
        getHotPosts()
    }


    private val _jobFeed: MutableLiveData<Resource<JobFeed>> = MutableLiveData()
    val jobFeed: LiveData<Resource<JobFeed>> get() = _jobFeed

    private fun getHotPosts() = viewModelScope.launch(Dispatchers.IO) {
        _jobFeed.postValue(Resource.Loading())
        val response = repository.getJobFeed()
        _jobFeed.postValue(handleJobFeed(response))
    }

    private fun handleJobFeed(response: Response<JobFeed>): Resource<JobFeed> {
        if (response.isSuccessful){
            response.body()?.let { jobFeed ->
                return Resource.Success(jobFeed)
            }
        }
        return Resource.Error(response.message())
    }


}