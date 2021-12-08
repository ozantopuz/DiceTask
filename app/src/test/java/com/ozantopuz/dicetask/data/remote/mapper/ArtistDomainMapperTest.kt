package com.ozantopuz.dicetask.data.remote.mapper

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.remote.entity.ArtistResponse
import com.ozantopuz.dicetask.data.remote.entity.getArtistResponse
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistDomainMapperTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var mapper: ArtistDomainMapper

    @Before
    fun setUp() {
        mapper = ArtistDomainMapper()
    }

    @Test
    fun `mapper should map remote data source item to domain item type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val remoteDataSourceItem = getArtistResponse()

            // When
            val domainItem = mapper.map(remoteDataSourceItem)

            // Then
            Truth.assertThat(domainItem).isNotNull()
            Truth.assertThat(domainItem.id).isEqualTo("f27ec8db-af05-4f36-916e-3d57f91ecf5e")
            Truth.assertThat(domainItem.score).isEqualTo(94)
        }
}