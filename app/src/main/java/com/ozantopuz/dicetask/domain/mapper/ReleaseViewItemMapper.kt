package com.ozantopuz.dicetask.domain.mapper

import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.ui.entity.ReleaseViewItem
import com.ozantopuz.dicetask.util.extension.ignoreNull
import com.ozantopuz.dicetask.util.mapper.Mapper

class ReleaseViewItemMapper : Mapper<Release, ReleaseViewItem> {

    override suspend fun map(item: Release): ReleaseViewItem =
        ReleaseViewItem(
            disambiguation = item.disambiguation.ignoreNull(),
            id = item.id.ignoreNull(),
            title = item.title.ignoreNull("-"),
            primaryTypeId = item.primaryTypeId.ignoreNull(),
            primaryType = item.primaryType.ignoreNull(),
            firstReleaseDate = item.firstReleaseDate.ignoreNull("-"),
        )
}