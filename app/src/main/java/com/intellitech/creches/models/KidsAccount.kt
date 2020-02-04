package com.intellitech.creches.models

data class KidsAccount(
    val balance: Balance,
    val kidProfile: KidProfile,
    val parent: Parent,
    val paiments: List<Paiment>
)