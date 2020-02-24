package com.intellitech.creches.models

data class Meal(
    val food: String,
    val img: String
) {
    constructor(): this("", "")
}