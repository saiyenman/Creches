package com.intellitech.creches.models

data class GourpProfile(
    val Type: String,
    val educatorProfile: EducatorProfile,
    val id: String,
    val title: String
)