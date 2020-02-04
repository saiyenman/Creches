package com.intellitech.creches.models

data class Parent(
    val notifications: List<Notification>,
    val parentProfile: ParentProfile,
    val loginAccount: LoginAccount
)