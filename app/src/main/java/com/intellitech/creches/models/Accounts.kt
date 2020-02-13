package com.intellitech.creches.models

data class Accounts(
    val kidAccounts: List<KidAccount>,
    val staffAccounts: List<StaffAccount>
) {
    constructor(): this(listOf(), listOf())
}