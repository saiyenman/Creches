package com.intellitech.creches.models

data class SectionProfile(
    val ageCategory: String,
    val id: String,
    val sectionSupervisorProfile: SectionSupervisorProfile,
    val title: String
)