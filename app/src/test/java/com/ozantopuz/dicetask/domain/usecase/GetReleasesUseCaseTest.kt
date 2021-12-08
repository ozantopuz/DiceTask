package com.ozantopuz.dicetask.domain.usecase

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.Result
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.domain.mapper.ReleaseViewItemMapper
import com.ozantopuz.dicetask.domain.repository.ArtistRepository
import com.ozantopuz.dicetask.shared.base.BaseTestClass
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import com.ozantopuz.dicetask.ui.entity.ReleaseViewItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetReleasesUseCaseTest : BaseTestClass() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @MockK
    private lateinit var repository: ArtistRepository

    @MockK
    private lateinit var mapper: ReleaseViewItemMapper

    @RelaxedMockK
    private lateinit var releaseViewItem: ReleaseViewItem

    private lateinit var useCase: GetReleasesUseCase

    override fun setUp() {
        super.setUp()
        useCase = GetReleasesUseCase(repository, mapper)
    }

    @Test
    fun `use case should return data properly`() = coroutinesTestRule.runBlockingTest {
        // Given
        coEvery {
            repository.fetchReleases(ReleasesParams(""))
        } coAnswers {
            Result.Success(listOf(Release()))
        }

        coEvery {
            mapper.map(any())
        }.coAnswers {
            releaseViewItem
        }

        // Then
        val result = useCase.execute(ReleasesParams(""))

        // Then
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result).isInstanceOf(Result.Success::class.java)

        coVerify(exactly = 1) { repository.fetchReleases(ReleasesParams("")) }
        coVerify(exactly = 1) { mapper.map(any()) }
        confirmVerified(repository)
        confirmVerified(mapper)
    }
}