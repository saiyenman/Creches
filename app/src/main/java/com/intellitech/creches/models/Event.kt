package com.intellitech.creches.models

class Event(
    val eventDescription: String,
    val eventPictures: List<String>,
    val eventDate: String,
    val eventTime: String,
    val eventTitle: String
) {
    constructor(): this("", listOf(), "","", "")
}