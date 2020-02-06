package com.intellitech.creches.models

data class Group(
    val events: List<Event>,
    val groupProfile: GroupProfile,
    val meals: List<Meal>,
    val other: List<Other>,
    val sessions: List<Session>
)