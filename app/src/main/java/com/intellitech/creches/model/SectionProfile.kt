package com.intellitech.creches.model

data class SectionProfile(
    val ageCategory: String,
    val id: String,
    val sectionSupervisorProfile: SectionSupervisorProfile,
    val title: String
)