package com.intellitech.creches.models

data class Day(
    val day: String,
    val dayDescription: String,
    val groups: List<Group>
)