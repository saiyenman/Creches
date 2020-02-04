package com.intellitech.creches.model

data class GroupX(
    val events: List<Event>,
    val gourpProfile: GourpProfileX,
    val meals: List<Meal>,
    val notifications: List<NotificationX>,
    val other: List<Other>,
    val section: SectionX,
    val sessions: List<Session>
)