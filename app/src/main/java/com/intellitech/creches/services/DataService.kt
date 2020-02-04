package com.intellitech.creches.services

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.intellitech.creches.models.*

object DataService {
    val parentKid1 = Parent(listOf(Notification("12-12-2019", "Your kid is gay", "12:00", "Alert")), ParentProfile("cite ok", "123456", "youcef", "goku", "0794567654", 13), LoginAccount("passparent", "0794567654@gmail.com"))
    val kidProfile1 = KidProfile("city 12", "12-12-2012", "Helouf", "123", "Amine", "Benbaha",12)
    val kidAccount1 = KidsAccount(Balance(2000, false) , kidProfile1, parentKid1, listOf())
    val accounts = Accounts(listOf(kidAccount1), listOf())
    val creche: Creche = Creche(accounts, Admin("admin", "98567567@gmail.com"),"creche123", KindergartenProfil("cite setif 14","crech123@gmail.com","islem benbaha", "djelloul", "021455667"), "Creche 123", listOf(), listOf())

    fun createDatabase() {
        val database = FirebaseDatabase.getInstance().reference
        database.child("creche123").setValue(creche)
            .addOnSuccessListener {
                Log.d("firebase", "success")
            }
            .addOnFailureListener {
                Log.d("firebase", it.message)
            }
    }
}