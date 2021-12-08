package com.ozantopuz.dicetask.ui.entity

import android.os.Parcelable
import com.ozantopuz.dicetask.util.entity.ViewItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistViewItem(
    val id: String,
    val type: String,
    val typeId: String,
    val score: String,
    val gender: String,
    val genderId: String,
    val name: String,
    val sortName: String,
    val country: String
) : ViewItem, Parcelable