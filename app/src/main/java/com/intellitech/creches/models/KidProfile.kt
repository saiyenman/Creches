package com.intellitech.creches.models

data class KidProfile(
    val id: String,
    val lastName: String,
    val name: String,
    val adress: String,
    val dateOfBirth: String,
    val healthCondition: String,
    val profilePic: Int
)