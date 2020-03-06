package com.intellitech.creches.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class KidAccount(
    val id:Long,
    val status:Boolean,
    val balance: @RawValue Balance?,
    val section : String,
    val group: String,
    val kidProfile: @RawValue KidProfile?,
    val parent: @RawValue Parent?,
    val payments: @RawValue List<Payment>?
):Parcelable {
    constructor(): this(0,true,null,"","", null, null, listOf())
}