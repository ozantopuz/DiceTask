package com.ozantopuz.dicetask.ui.entity

import com.ozantopuz.dicetask.util.entity.ViewItem

data class ReleaseViewItem(
    val disambiguation: String,
    val id: String,
    val title: String,
    val primaryTypeId: String,
    val primaryType: String,
    val firstReleaseDate: String
) : ViewItem