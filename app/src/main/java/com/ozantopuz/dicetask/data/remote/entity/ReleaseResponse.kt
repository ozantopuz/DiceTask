package com.ozantopuz.dicetask.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ozantopuz.dicetask.util.entity.RemoteDataSourceItem

data class ReleaseResponse(
    val disambiguation: String?,
    val id: String?,
    val title: String?,
    @SerializedName("primary-type-id")
    val primaryTypeId: String?,
    @SerializedName("primary-type")
    val primaryType: String?,
    @SerializedName("first-release-date")
    val firstReleaseDate: String?
) : RemoteDataSourceItem