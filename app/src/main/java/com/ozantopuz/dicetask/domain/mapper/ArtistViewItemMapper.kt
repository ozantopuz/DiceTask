package com.ozantopuz.dicetask.domain.mapper

import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.ui.entity.ArtistViewItem
import com.ozantopuz.dicetask.util.extension.ignoreNull
import com.ozantopuz.dicetask.util.mapper.Mapper

class ArtistViewItemMapper : Mapper<Artist, ArtistViewItem> {

    override suspend fun map(item: Artist): ArtistViewItem =
        ArtistViewItem(
            id = item.id.ignoreNull(),
            type = item.type.ignoreNull("-"),
            typeId = item.typeId.ignoreNull(),
            score = item.score.toString(),
            gender = item.gender.ignoreNull(),
            genderId = item.genderId.ignoreNull(),
            name = item.name.ignoreNull("-"),
            sortName = item.sortName.ignoreNull(),
            country = item.country.ignoreNull("-")
        )
}