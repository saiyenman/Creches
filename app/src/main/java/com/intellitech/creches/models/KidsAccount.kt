package com.intellitech.creches.models

data class KidsAccount(
    val balance: BalanceX,
    val kidProfile: KidProfile,
    val loginAccount: LoginAccount,
    val notifications: List<Notification>,
    val paiments: List<Paiment>
)