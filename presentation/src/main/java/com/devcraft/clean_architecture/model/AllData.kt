package com.devcraft.clean_architecture.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class AllData(
    val id : Long,
    val title : String,
    val date : Long,
    val categories : @RawValue MutableList<Categories>) : Parcelable
