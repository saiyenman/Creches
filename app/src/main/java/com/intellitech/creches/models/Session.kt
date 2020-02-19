package com.intellitech.creches.models

data class Session(
    val sessionDescription: String,
    val sessionStartTime: String,
    val sessionEndTime: String
) {
    constructor(): this("","","")
}