package com.intellitech.creches.models

data class SectionSupervisorProfile(
    val email: String,
    val fullName: String,
    val id: String,
    val phoneNumber: String,
    val role: String
) {
    constructor(): this("", "", "", "", "")
}