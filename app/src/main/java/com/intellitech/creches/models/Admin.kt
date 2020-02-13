package com.intellitech.creches.models

data class Admin(
    val password: String,
    val user: String
) {
    constructor(): this("","")
}