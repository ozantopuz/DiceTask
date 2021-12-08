package com.ozantopuz.dicetask.data.remote.service

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.remote.entity.SearchResponse
import com.ozantopuz.dicetask.shared.dispatcher.ErrorDispatcher
import com.ozantopuz.dicetask.shared.dispatcher.SuccessDispatcher
import com.ozantopuz.dicetask.shared.dispatcher.TimeoutDispatcher
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class ArtistServiceTest {

    private val mockWebServer = MockWebServer()
    private lateinit var artistService: ArtistService

    @Before
    fun setUp() {
        mockWebServer.start()

        val client = OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor())
            connectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
            readTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
            writeTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
        }.build()

        val retrofit = Retrofit.Builder().apply {
            client(client)
            baseUrl(mockWebServer.url("/").toString())
            addConverterFactory(GsonConverterFactory.create())
        }.build()

        artistService = retrofit.create()

        mockWebServer.dispatcher = SuccessDispatcher(ARTIST_SCORE_SUCCESS_RESPONSE_FILE_NAME)
    }

    @Test
    fun `artists should be fetched`() = runBlocking {
        // Given

        // When
        val response = artistService.getArtists("",0,0)

        // Then
        Truth.assertThat(response).isNotNull()
        Truth.assertThat(response.count).isEqualTo(12)
    }

    @Test
    fun `artists service should throw an error`() = runBlocking {
        mockWebServer.dispatcher = ErrorDispatcher

        // Given
        var response: SearchResponse? = null

        // When
        try {
            response = artistService.getArtists("",0,0)
        } catch (e: Exception) {
            Truth.assertThat(e.message).contains("HTTP 404 Client Error")
        }

        // Then
        Truth.assertThat(response).isNull()
        Truth.assertThat(response?.count).isNull()
    }

    @Test
    fun `request should be timed out`() = runBlocking {
        mockWebServer.dispatcher = TimeoutDispatcher

        // Given
        var response: SearchResponse? = null

        // When
        try {
            response = artistService.getArtists("",0,0)
        } catch (e: Exception) {
            Truth.assertThat(e.message).isEqualTo("timeout")
        }

        // Then
        Truth.assertThat(response).isNull()
        Truth.assertThat(response?.count).isNull()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private companion object {
        private const val TIMEOUT_IN_MS = 1000L
        private const val ARTIST_SCORE_SUCCESS_RESPONSE_FILE_NAME = "artist_success_response.json"
    }
}
