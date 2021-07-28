package com.ccd.listdetailsample.business.model

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("status")
    val status: String,

    @SerializedName("copyright")
    val copyright: String,

    @SerializedName("num_results")
    val num_results: Int,

    @SerializedName("results")
    val results: List<Article>
)
