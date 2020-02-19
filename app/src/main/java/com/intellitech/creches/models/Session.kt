package com.intellitech.creches.models

data class Session(
    val sessionTitle: String,
    val sessionStartTime: String
) {
    constructor(): this("","")
}