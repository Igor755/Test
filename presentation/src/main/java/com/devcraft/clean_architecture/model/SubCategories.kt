package com.devcraft.clean_architecture.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SubCategories(
    val id : Long,
    val title : String,
    val date : Long
) : Parcelable
