package com.ozantopuz.dicetask.data.remote.mapper

import com.ozantopuz.dicetask.data.remote.entity.ArtistResponse
import com.ozantopuz.dicetask.domain.entity.Artist
import com.ozantopuz.dicetask.util.mapper.Mapper

class ArtistDomainMapper : Mapper<ArtistResponse, Artist> {

    override suspend fun map(item: ArtistResponse): Artist =
        Artist(
            id = item.id,
            type = item.type,
            typeId = item.typeId,
            score = item.score,
            gender = item.gender,
            genderId = item.genderId,
            name = item.name,
            sortName = item.sortName,
            country = item.country
        )
}