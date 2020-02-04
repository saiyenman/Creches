package com.intellitech.creches.models

data class Group(
    val events: List<Event>,
    val groupProfile: GourpProfileX,
    val meals: List<Meal>,
    val other: List<Other>,
    val section: Section,
    val sessions: List<Session>
)