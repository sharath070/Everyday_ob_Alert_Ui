package com.sharath070.everydayjobalertui.api

import com.sharath070.everydayjobalertui.model.jobSearchModel.JobFeed
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {

    @GET("posts")
    @Headers("Cache-Control: max-age=60")
    suspend fun getJobFeed(): Response<JobFeed>
}