package com.intellitech.creches.models

data class Paiment(
    val paimentAmmount: Int,
    val paimentDate: String
) {
    constructor(): this(0, "")
}