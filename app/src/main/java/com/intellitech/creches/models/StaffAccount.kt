package com.intellitech.creches.models

data class StaffAccount(
    val loginAccount: LoginAccount?,
    val staffProfile: StaffProfile?
) {
    constructor(): this(null, null)
}