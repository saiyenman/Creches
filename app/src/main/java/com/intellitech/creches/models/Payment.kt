package com.intellitech.creches.models

data class Payment(
    val paimentAmmount: Int,
    val paimentDate: String,
    val description: String,
    val paid:Boolean
) {
    constructor(): this(0, "","",false)
}