package com.ozantopuz.dicetask.data.remote.entity

import com.ozantopuz.dicetask.util.entity.RemoteDataSourceItem

data class SearchResponse(
    val created: String?,
    val count: Int?,
    val offset: Int?,
    val artists: List<ArtistResponse>?
) : RemoteDataSourceItem