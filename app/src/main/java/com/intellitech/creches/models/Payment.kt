package com.intellitech.creches.models

data class Payment(
    val paimentAmmount: Int,
    val paimentDate: String,
    val description: String
) {
    constructor(): this(0, "","")
}