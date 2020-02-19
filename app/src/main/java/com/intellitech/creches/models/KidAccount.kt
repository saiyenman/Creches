package com.intellitech.creches.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class KidAccount(
    val balance: @RawValue Balance?,
    val section : String,
    val group: String,
    val kidProfile: @RawValue KidProfile?,
    val parent: @RawValue Parent?,
    val payments: @RawValue List<Payment>?
) : Parcelable {
    constructor(): this(null,"","", null, null, listOf())
}