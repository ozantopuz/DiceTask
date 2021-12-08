package com.ozantopuz.dicetask.data.remote.mapper

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.data.remote.entity.ArtistResponse
import com.ozantopuz.dicetask.data.remote.entity.ReleaseResponse
import com.ozantopuz.dicetask.data.remote.entity.getReleaseResponse
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ReleaseDomainMapperTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var mapper: ReleaseDomainMapper

    @Before
    fun setUp() {
        mapper = ReleaseDomainMapper()
    }

    @Test
    fun `mapper should map remote data source item to domain item type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val remoteDataSourceItem = getReleaseResponse()

            // When
            val domainItem = mapper.map(remoteDataSourceItem)

            // Then
            Truth.assertThat(domainItem).isNotNull()
            Truth.assertThat(domainItem.id).isEqualTo("f32fab67-77dd-3937-addc-9062e28e4c37")
            Truth.assertThat(domainItem.title).isEqualTo("Thriller")
        }
}