package com.intellitech.creches.models

data class Session(
    val sessionDescriotion: String,
    val sessionTheme: String,
    val sessionTiming: String,
    val sessionTitle: String,
    val sessionType: String,
    val type: String
) {
    constructor(): this("","","","","","")
}