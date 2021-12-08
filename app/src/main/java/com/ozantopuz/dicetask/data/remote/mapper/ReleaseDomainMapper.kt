package com.ozantopuz.dicetask.data.remote.mapper

import com.ozantopuz.dicetask.data.remote.entity.ReleaseResponse
import com.ozantopuz.dicetask.domain.entity.Release
import com.ozantopuz.dicetask.util.mapper.Mapper

class ReleaseDomainMapper : Mapper<ReleaseResponse, Release> {

    override suspend fun map(item: ReleaseResponse): Release =
        Release(
            disambiguation = item.disambiguation,
            id = item.id,
            title = item.title,
            primaryTypeId = item.primaryTypeId,
            primaryType = item.primaryType,
            firstReleaseDate = item.firstReleaseDate
        )
}