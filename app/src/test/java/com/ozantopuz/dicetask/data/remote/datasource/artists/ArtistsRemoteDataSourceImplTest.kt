package com.ozantopuz.dicetask.data.remote.datasource.artists

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.remote.entity.ArtistResponse
import com.ozantopuz.dicetask.data.remote.entity.SearchResponse
import com.ozantopuz.dicetask.data.remote.entity.getArtistResponse
import com.ozantopuz.dicetask.data.remote.mapper.ArtistDomainMapper
import com.ozantopuz.dicetask.data.remote.service.ArtistService
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.shared.base.BaseTestClass
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistsRemoteDataSourceImplTest : BaseTestClass() {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var artistService: ArtistService

    @MockK
    private lateinit var mapper: ArtistDomainMapper

    @RelaxedMockK
    private lateinit var artistResponse: ArtistResponse

    private lateinit var dataSource: ArtistsRemoteDataSource

    override fun setUp() {
        super.setUp()
        dataSource = ArtistsRemoteDataSourceImpl(artistService, mapper)
    }

    @Test
    fun `data source should return data`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            artistService.getArtists("", 0, 0)
        }.coAnswers {
            SearchResponse(null, null, null, listOf(artistResponse))
        }

        coEvery {
            mapper.map(any())
        }.coAnswers {
            Artist()
        }

        // When
        val result: Result<List<Artist>> = dataSource.fetchArtists(ArtistsParams("", 0, 0))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)

        coVerify(exactly = 1) { artistService.getArtists("", 0, 0) }
        coVerify(exactly = 1) { mapper.map(any()) }
        confirmVerified(artistService)
        confirmVerified(mapper)
    }
}