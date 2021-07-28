package com.ccd.listdetailsample.data.repository

import com.ccd.listdetailsample.data.network.RetrofitClient
import retrofit2.http.Path

class AppRepository {
    suspend fun getList(
        @Path("section") section: String,
        @Path("period") period: String
    ) = RetrofitClient.nytimesArticles.getArticles(section,period)
}