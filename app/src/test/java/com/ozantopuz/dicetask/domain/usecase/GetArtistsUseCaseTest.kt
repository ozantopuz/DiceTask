package com.ozantopuz.dicetask.domain.usecase

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.domain.mapper.ArtistViewItemMapper
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.shared.base.BaseTestClass
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetArtistsUseCaseTest : BaseTestClass() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: ArtistRepository

    @MockK
    private lateinit var mapper: ArtistViewItemMapper

    @RelaxedMockK
    private lateinit var artistViewItem: ArtistViewItem

    private lateinit var useCase: GetArtistsUseCase

    override fun setUp() {
        super.setUp()
        useCase = GetArtistsUseCase(repository, mapper)
    }

    @Test
    fun `use case should return data properly`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            repository.fetchArtists(ArtistsParams("", 0, 0))
        } coAnswers {
            Result.Success(listOf(Artist()))
        }

        coEvery {
            mapper.map(any())
        }.coAnswers {
            artistViewItem
        }

        // Then
        val result = useCase.execute(ArtistsParams("", 0, 0))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)

        coVerify(exactly = 1) { repository.fetchArtists(ArtistsParams("", 0, 0)) }
        coVerify(exactly = 1) { mapper.map(any()) }
        confirmVerified(repository)
        confirmVerified(mapper)
    }
}