package com.ccd.listdetailsample.data.network

import com.ccd.listdetailsample.business.model.Article
import com.ccd.listdetailsample.business.model.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface API {
    @GET("/svc/mostpopular/v2/mostviewed/{section}/{period}.json?api-key=bAd2rpActA7e26XFMh8q4UlejsgqMEB1")
    suspend fun getArticles(
        @Path("section") section: String,
        @Path("period") period: String
    ): Response
}