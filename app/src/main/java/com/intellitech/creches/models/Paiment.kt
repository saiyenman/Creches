package com.intellitech.creches.models

data class Paiment(
    val paimentAmount: Int,
    val paimentDate: String
) {
    constructor(): this(0, "")
}