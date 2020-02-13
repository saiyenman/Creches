package com.intellitech.creches.models

data class Group(
    val events: List<Event>?,
    val groupProfile: GroupProfile?,
    val kids: List<KidAccount>?,
    val meals: List<Meal>?,
    val other: List<Other>?,
    val sessions: List<Session>?
) {
    constructor(): this(null, null, null, null, null, null)
}