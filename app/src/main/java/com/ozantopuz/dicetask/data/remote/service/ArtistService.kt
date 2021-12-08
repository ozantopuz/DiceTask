package com.ozantopuz.dicetask.data.remote.service

import com.ozantopuz.dicetask.data.remote.entity.ReleasesResponse
import com.ozantopuz.dicetask.data.remote.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistService {

    @GET("artist")
    suspend fun getArtists(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): SearchResponse

    @GET("release-group")
    suspend fun getReleases(
        @Query("artist") artist: String
    ): ReleasesResponse
}