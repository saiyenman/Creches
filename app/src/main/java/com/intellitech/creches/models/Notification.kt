package com.intellitech.creches.models

data class Notification(
    val notificationDate: String,
    val notificationText: String,
    val notificationTime: String,
    val notificationTitle: String
) {
    constructor(): this("", "", "", "")
}