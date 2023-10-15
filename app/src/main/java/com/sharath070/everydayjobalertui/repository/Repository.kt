package com.sharath070.everydayjobalertui.repository

import com.sharath070.everydayjobalertui.api.RetrofitInstance

class Repository {

    suspend fun getJobFeed() = RetrofitInstance.api.getJobFeed()

}