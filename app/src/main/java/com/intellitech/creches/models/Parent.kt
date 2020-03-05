package com.intellitech.creches.models

data class Parent(
    val notifications: List<Notification>?,
    val parentProfile: ParentProfile?,
    val loginAccount: LoginAccount?,
    val messagingToken: String
) {
    constructor(): this(null, null, null, "")
}