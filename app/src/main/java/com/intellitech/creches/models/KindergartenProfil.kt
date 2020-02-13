package com.intellitech.creches.models

data class KindergartenProfil(
    val adress: String,
    val email: String,
    val manager: String,
    val owner: String,
    val phoneNumber: String
) {
    constructor(): this("", "", "", "", "")
}