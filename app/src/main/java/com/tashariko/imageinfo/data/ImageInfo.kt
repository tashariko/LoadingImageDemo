package com.tashariko.imageinfo.data

import com.google.gson.annotations.SerializedName

data class ImageInfo(
    @SerializedName("created_on")
    val createdOn: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("payment_plan")
    val paymentPlan: String,
    @SerializedName("posterLink")
    val posterLink: String,
    @SerializedName("release_year")
    val releaseYear: Int,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("updated_on")
    val updatedOn: String?,
    @SerializedName("video_duration")
    val videoDuration: String
)