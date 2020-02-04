package com.intellitech.creches.model

data class creche(
    val accounts: Accounts,
    val admin: Admin,
    val id: String,
    val kindergartenProfil: KindergartenProfil,
    val name: String,
    val sections: List<Section>,
    val years: List<Year>
)