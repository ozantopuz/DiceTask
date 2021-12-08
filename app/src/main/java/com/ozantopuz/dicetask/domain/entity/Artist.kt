package com.ozantopuz.dicetask.domain.entity

import com.ozantopuz.dicetask.util.entity.DomainItem

data class Artist(
    val id: String? = null,
    val type: String? = null,
    val typeId: String? = null,
    val score: Int? = null,
    val gender: String? = null,
    val genderId: String? = null,
    val name: String? = null,
    val sortName: String? = null,
    val country: String? = null
) : DomainItem