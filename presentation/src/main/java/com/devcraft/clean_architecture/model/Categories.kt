package com.devcraft.clean_architecture.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Categories(
    val id : Long,
    val title : String,
    val date : Long,
    val subCategories:  @RawValue MutableList<SubCategories>?) : Parcelable
