package com.ozantopuz.dicetask.domain.entity

import com.ozantopuz.dicetask.util.entity.DomainItem

data class Release(
    val disambiguation: String? = null,
    val id: String? = null,
    val title: String? = null,
    val primaryTypeId: String? = null,
    val primaryType: String? = null,
    val firstReleaseDate: String? = null
) : DomainItem