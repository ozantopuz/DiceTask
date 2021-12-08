package com.ozantopuz.dicetask.domain.mapper

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.domain.entity.getArtist
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtistViewItemMapperTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var mapper: ArtistViewItemMapper

    @Before
    fun setUp() {
        mapper = ArtistViewItemMapper()
    }

    @Test
    fun `mapper should map domain item to view item type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val domainItem = getArtist()

            // When
            val viewItem = mapper.map(domainItem)

            // Then
            Truth.assertThat(viewItem).isNotNull()
            Truth.assertThat(viewItem.id).isEqualTo("f27ec8db-af05-4f36-916e-3d57f91ecf5e")
            Truth.assertThat(viewItem.score).isEqualTo("94")
        }
}