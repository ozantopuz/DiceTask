package com.ozantopuz.dicetask.data.remote.datasource.releases

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSource
import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSourceImpl
import com.ozantopuz.dicetask.data.remote.entity.*
import com.ozantopuz.dicetask.data.remote.mapper.ArtistDomainMapper
import com.ozantopuz.dicetask.data.remote.mapper.ReleaseDomainMapper
import com.ozantopuz.dicetask.data.remote.service.ArtistService
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams
import com.ozantopuz.dicetask.shared.base.BaseTestClass
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ReleasesRemoteDataSourceImplTest : BaseTestClass() {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var artistService: ArtistService

    @MockK
    private lateinit var mapper: ReleaseDomainMapper

    @RelaxedMockK
    private lateinit var releaseResponse: ReleaseResponse

    private lateinit var dataSource: ReleasesRemoteDataSource

    override fun setUp() {
        super.setUp()
        dataSource = ReleasesRemoteDataSourceImpl(artistService, mapper)
    }

    @Test
    fun `data source should return data`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            artistService.getReleases("")
        }.coAnswers {
            ReleasesResponse(null, listOf(releaseResponse) ,null)
        }

        coEvery {
            mapper.map(any())
        }.coAnswers {
            Release()
        }

        // When
        val result: Result<List<Release>> = dataSource.fetchReleases(ReleasesParams(""))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)

        coVerify(exactly = 1) { artistService.getReleases("") }
        coVerify(exactly = 1) { mapper.map(any()) }
        confirmVerified(artistService)
        confirmVerified(mapper)
    }
}