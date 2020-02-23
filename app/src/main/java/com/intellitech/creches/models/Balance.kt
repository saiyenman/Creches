package com.intellitech.creches.models

data class Balance(
    val amount: Int,
    val overdue: Boolean
) {
    constructor(): this(0, false)
}