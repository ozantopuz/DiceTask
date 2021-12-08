package com.ozantopuz.dicetask.data.remote.entity

import com.google.gson.annotations.SerializedName
import com.ozantopuz.dicetask.util.entity.RemoteDataSourceItem

data class ReleasesResponse(
    @SerializedName("release-group-count")
    val releaseGroupCount: Int?,
    @SerializedName("release-groups")
    val releases: List<ReleaseResponse>?,
    @SerializedName("release-group-offset")
    val releaseGroupOffset: Int?
) : RemoteDataSourceItem