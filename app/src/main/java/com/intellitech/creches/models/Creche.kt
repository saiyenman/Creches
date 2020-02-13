package com.intellitech.creches.models

data class Creche(
    val accounts: Accounts?,
    val admin: Admin?,
    val id: String?,
    val kindergartenProfil: KindergartenProfil?,
    val name: String?,
    val sections: List<Section>?,
    val years: List<Year>?
) {
    constructor(): this(null, null, "", null, "", null, null)
}