package com.timmy.hiltmvvm.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers("Accept: application/json")
    @GET("test")
    suspend fun getData(@Query("country") countryCode: String = "us"): SampleDataFromAPI

}