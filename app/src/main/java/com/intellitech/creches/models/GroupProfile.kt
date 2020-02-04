package com.intellitech.creches.models

data class GroupProfile(
    val Type: String,
    val educatorProfile: EducatorProfile,
    val id: String,
    val title: String
)