package com.intellitech.creches.models

data class Day(
    val day: Int,
    val dayProfil: DayProfil,
    val groups: List<Group>
)