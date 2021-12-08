package com.ozantopuz.dicetask.domain.mapper

import com.google.common.truth.Truth
import com.ozantopuz.dicetask.domain.entity.getRelease
import com.ozantopuz.dicetask.shared.extension.runBlockingTest
import com.ozantopuz.dicetask.shared.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ReleaseViewItemMapperTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private lateinit var mapper: ReleaseViewItemMapper

    @Before
    fun setUp() {
        mapper = ReleaseViewItemMapper()
    }

    @Test
    fun `mapper should map domain item to view item type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val domainItem = getRelease()

            // When
            val viewItem = mapper.map(domainItem)

            // Then
            Truth.assertThat(viewItem).isNotNull()
            Truth.assertThat(viewItem.id).isEqualTo("f32fab67-77dd-3937-addc-9062e28e4c37")
            Truth.assertThat(viewItem.title).isEqualTo("Thriller")
        }
}