package com.intellitech.creches.models

data class Section(
    val groups: List<Group>?,
    val sectionProfile: SectionProfile?
) {
    constructor(): this(null, null)
}