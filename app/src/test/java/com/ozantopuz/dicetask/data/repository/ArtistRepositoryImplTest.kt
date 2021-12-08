package com.ozantopuz.dicetask.data.repository

import com.ozantopuz.dicetask.data.Result
import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.remote.datasource.artists.ArtistsRemoteDataSource
import com.ozantopuz.dicetask.data.remote.datasource.releases.ReleasesRemoteDataSource
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.domain.usecase.ArtistsParams
import com.ozantopuz.dicetask.domain.usecase.ReleasesParams
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
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistRepositoryImplTest : BaseTestClass() {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var artistsRemoteDataSource: ArtistsRemoteDataSource

    @MockK
    private lateinit var releasesRemoteDataSource: ReleasesRemoteDataSource

    @RelaxedMockK
    private lateinit var artist: Artist

    @RelaxedMockK
    private lateinit var release: Release

    private lateinit var repository: ArtistRepository

    override fun setUp() {
        super.setUp()
        repository = ArtistRepositoryImpl(artistsRemoteDataSource, releasesRemoteDataSource)
    }

    @Test
    fun `repository should return artist data`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            artistsRemoteDataSource.fetchArtists(ArtistsParams("",0,0))
        } coAnswers  {
            Result.Success(listOf(artist))
        }

        every {
            artist.score
        } returns 94

        // When
        val result = repository.fetchArtists(ArtistsParams("",0,0))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data.first().score).isEqualTo(94)

        coVerify(exactly = 1) {
            artistsRemoteDataSource.fetchArtists(ArtistsParams("",0,0))
        }
        confirmVerified(artistsRemoteDataSource)
    }

    @Test
    fun `repository should return release data`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            releasesRemoteDataSource.fetchReleases(ReleasesParams(""))
        } coAnswers  {
            Result.Success(listOf(release))
        }

        every {
            release.title
        } returns "Thriller"

        // When
        val result = repository.fetchReleases(ReleasesParams(""))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)
        Truth.assertThat((result as Result.Success).data.first().title).isEqualTo("Thriller")

        coVerify(exactly = 1) {
            releasesRemoteDataSource.fetchReleases(ReleasesParams(""))
        }
        confirmVerified(releasesRemoteDataSource)
    }
}