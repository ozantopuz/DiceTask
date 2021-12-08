package com.ozantopuz.dicetask.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ozantopuz.dicetask.util.entity.RemoteDataSourceItem

data class ArtistResponse(
    val id: String?,
    val type: String?,
    @SerializedName("type-id")
    val typeId: String?,
    val score: Int?,
    val gender: String?,
    @SerializedName("gender-id")
    val genderId: String?,
    val name: String?,
    @SerializedName("sort-name")
    val sortName: String?,
    val country: String?
) : RemoteDataSourceItem