package com.ccd.listdetailsample.business.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("published_date")
    var published_date: String,

    @SerializedName("byline")
    var byline: String,

    @SerializedName("url")
    var url: String,

    @SerializedName("media")
    var media: List<Media>
)

data class Media(
    @SerializedName("type")
    var type: String,

    @SerializedName("media-metadata")
    var metadata: List<MetaData>
)

data class MetaData(
    @SerializedName("url")
    var url: String,
)
