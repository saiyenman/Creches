package com.intellitech.creches.models

data class EducatorProfile(
    val email: String,
    val fullName: String,
    val id: String,
    val job: String,
    val phoneNumber: String
) {
    constructor(): this("", "", "", "", "" )
}