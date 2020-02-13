package com.intellitech.creches.services

import android.util.Log
import com.google.firebase.database.*
import com.intellitech.creches.interfaces.FirebaseDataInterface
import com.intellitech.creches.models.*

object DataService {
    val database = FirebaseDatabase.getInstance().reference

    val parentKid1 = Parent(listOf(
        Notification("12-12-2019", "Your kid is gay"
            , "12:00", "Alert")
        ,Notification("02-09-2002", "وليدك دار يوطار اوسمك"
            , "13:00", "Alert")
        ,Notification("10-02-2004", "وليدك عندو سمانة ماجاش"
            , "15:00", "Alert"))
        , ParentProfile("Bouraoui Ammar", "123456", "Mohammed", "Salah"
            , "0794567654", 13), LoginAccount("passparent", "0794567654@gmail.com"))


    val parentKid2 = Parent(listOf(Notification("12-12-2019", "wlidk ychouf 3ndi"
        , "12:00", "Alert"),Notification("12-12-2019", "البانان لازم مايفوتش 25 دينار"
        , "21:00", "اعلان"))
        , ParentProfile("حي المظاهرات", "234567", "Mahrez", "ALDJIN"
            , "0675913511", 13), LoginAccount("passparent", "0675913511@gmail.com"))


    val parentKid3 = Parent(listOf(Notification("12-12-2019", "Chouf kachma dir lwlidk aw may7chmch"
        , "09:00", "Alert"))
        , ParentProfile("cite ok", "123456", "Islam", "Xayah"
            , "0675495713", 13), LoginAccount("passparent", "0675495713@gmail.com"))
    val kidProfile1 = KidProfile("city 13", "Sayah", "Islam", "Ain Oulmane", "12-02-1995", " allergie,cold",12)
    val kidProfile2 = KidProfile("city 14", "Ghanemi", "Aziz", "Tikejda", "27-11-1995", "worm",12)
    val kidProfile3 = KidProfile("city 15", "Benaha", "Amine", "Ras El Aioun", "27-11-1996", "very very good",12)

    val kidAccount1 = KidAccount(Balance(2000, false) , kidProfile1, parentKid1, listOf())
    val kidAccount2 = KidAccount(Balance(3800, false) , kidProfile1, parentKid1, listOf())
    val kidAccount3 = KidAccount(Balance(900, true) , kidProfile2, parentKid1, listOf())

    val staffAccount1=StaffAccount(LoginAccount("12345","0675757575"),StaffProfile("0675757575@gmail.com","Fares Staff","0011","0675757575","CPA1"))
    val staffAccount2=StaffAccount(LoginAccount("12345","0666666666"),StaffProfile("0666666666@gmail.com","Nassima Staff","0012","0666666666","CPA2"))

    val session1=Session("in this session kids are going to learn surat al naml","Quran","8:00-10:00","al naml","Quran","in this session kids are going to learn surat al naml")
    val session2=Session("in this session kids are going to learn surat al naml","Quran","13:00-15:00","al kahf","Quran","in this session kids are going to learn surat al kahf")


    val groupeprofile1=GroupProfile("type 1",
        EducatorProfile("0675757575@gmail.com","Fares GHrr","GN01","Job test","0675757575"),"G01","القروش"
    )
    val groupeprofile2=GroupProfile("type 1",
        EducatorProfile("0675757575@gmail.com","Fares GHrr","GN02","Job test","0675757575"),"G02","الانانيش"
    )
    /*val event1=Event("حضانة creche123 تهنئكم على ذكرى يوم العلم", arrayListOf("https://i.picsum.photos/id/716/200/300.jpg","https://i.picsum.photos/id/288/200/300.jpg","https://i.picsum.photos/id/852/200/300.jpg"),"16-04-2020","يوم العلم","01")
    val event2=Event("حضانة creche123 تهنئكم على عيد الام", listOf(), """13-05-2020","عيد الام","02")
    val meal1=Meal("حليب","meal menu","09:00")
    val meal2=Meal("كروفات","meal menu","12:00")
    val meal3=Meal("حليب","meal menu","15:00")
    val other1=Other("description1","13:00","title")
    val other2=Other("description2","14:00","title2")*/

    /*val groupe1=Group(listOf(event1,event2),groupeprofile1,listOf(meal1,meal2,meal3),listOf(
        other1, other2), listOf(session1,session2))*/


    /*val groupe2=Group(listOf(event1,event2),groupeprofile1,listOf(meal1,meal2,meal3),listOf(
        other1, other2), listOf(session1,session2))
    val groupe3=Group(listOf(event1,event2),groupeprofile2,listOf(meal1,meal2,meal3),listOf(
        other1, other2), listOf(session1,session2))*/


    var sectionProfile1=SectionProfile("15","01",
        SectionSupervisorProfile("0675757575@gmail.com","Fares GHrr","S1","0675757575","001"),"section 1"
    )
    var sectionProfile2=SectionProfile("15","02",
        SectionSupervisorProfile("0675757575@gmail.com","Fares GHrr","S2","0675757575","001"),"section 2"
    )
   /* val section1=Section(listOf(groupe1,groupe2,groupe3), sectionProfile1)
    var section2=Section(listOf(groupe2,groupe1), sectionProfile2)

    var day1=Day("01", "", listOf(groupe1, groupe2,groupe3))
    var day2=Day("02", "", listOf(groupe1, groupe2,groupe3))
    var day3=Day("15", "", listOf(groupe1, groupe2,groupe3))
    var day4=Day("01", "", listOf(groupe1, groupe2,groupe3))
    var day5=Day("01", "", listOf(groupe1, groupe2,groupe3))*/
    /*var month1=Month(listOf(day1,day2, day3),"01")
    var month2=Month(listOf(day1,day2, day3,day5),"03")*/

    /*val accounts = Accounts(listOf(kidAccount1,kidAccount2,kidAccount3), listOf(staffAccount1,
        staffAccount2))
    val creche: Creche = Creche(
        accounts
        , Admin("admin", "98567567@gmail.com")
        , "creche123"
        , KindergartenProfil("cite setif 14","crech123@gmail.com","islem benbaha", "djelloul", "021455667"), "Creche 123"
        , listOf(
            section1, section2)
        ,listOf(
            Year(listOf(month1,month2),"2020")))*/

    fun createDatabase() {
        val monthsRef = database.child("creche123/years/2020/months/1/days/1/groups")

    }

    fun getSingleEvent(eventListener: FirebaseDataInterface): Event? {
        var event: Event? = null
        val eventRef = database.child("creche123/years/2020/months/1/days/0/groups/0/events").child("1")
        eventRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                event = p0.getValue(Event::class.java)
                eventListener.onEventDataFetched(event!!)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
        return event
    }

    fun getParentKids(phone: String, kidsResult: (List<KidAccount>) -> Unit) {
        val kidsRef = database.child("creche123/accounts/kidsAccounts")
        kidsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val kidsList = mutableListOf<KidAccount>()
                for(kidAccount in p0.children) {
                    val kid = kidAccount.getValue(KidAccount::class.java)
                    if (kid != null) {
                        if (kid.parent?.loginAccount!!.user == "$phone@gmail.com") {
                            kidsList.add(kid)
                        }
                    }
                }
                kidsResult(kidsList)
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    fun getKidsGroupEvents(phone: String, events: (List<Event>) -> Unit) {
        val sectionsRef = database.child("creche123/sections")
        sectionsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                for (section in p0.children) {
                    val sectionObject = section.getValue(Section::class.java)
                    sectionObject!!.groups!!.forEach {grp ->
                        val group = grp
                        group.kids!!.forEach {kidAcnt ->
                            val kidAccount = kidAcnt
                            if (kidAccount.parent!!.loginAccount!!.user == "$phone@gmail.com") {
                                events(group.events!!)
                            }
                        }
                    }
                }
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }
}