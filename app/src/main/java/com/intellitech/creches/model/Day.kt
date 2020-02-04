package com.intellitech.creches.model

data class Day(
    val day: Int,
    val dayProfil: DayProfil,
    val groups: List<GroupX>,
    val parents: List<Parent>
)