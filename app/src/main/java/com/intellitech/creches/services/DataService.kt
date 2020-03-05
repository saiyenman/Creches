package com.intellitech.creches.services

import android.util.Log
import com.google.firebase.database.*
import com.intellitech.creches.MainActivity
import com.intellitech.creches.interfaces.FirebaseDataInterface
import com.intellitech.creches.models.*

object DataService {
    private val database = FirebaseDatabase.getInstance().reference

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

    fun getKindergartenEvents(events: (List<Event>) -> Unit) {
        val eventsRef = database.child("creche123/events")
        eventsRef.addValueEventListener(object : ValueEventListener {
            val eventList = mutableListOf<Event>()
            override fun onDataChange(p0: DataSnapshot) {
                for (event in p0.children) {
                    val eventObject = event.getValue(Event::class.java)
                    eventList.add(eventObject!!)
                }
                events(eventList)
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    fun getDayCalendar(day: String, kid: KidAccount, calendar: (List<Session>) -> Unit) {
        val calendarRef = database.child("creche123/sections/${kid.section}/groups/${kid.group}/calendar/${day.toLowerCase()}")
        calendarRef.addValueEventListener(object : ValueEventListener {
            val sessionList = mutableListOf<Session>()
            override fun onDataChange(p0: DataSnapshot) {
                for (session in p0.children) {
                    val sessionObject = session.getValue(Session::class.java)
                    sessionList.add(sessionObject!!)
                }
                calendar(sessionList)
            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }


    fun fetchTuitions(kid:KidAccount, resultPayments:(List<Payment>)->Unit){
        val database = FirebaseDatabase.getInstance().reference
        val tuitionsRef= database.child("creche123/accounts/kidsAccounts/"+kid.kidProfile!!.id+"/payments")

        tuitionsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val listPayments= mutableListOf<Payment>()
                p0.children.forEach{
                    val payment=it.getValue(Payment::class.java)
                    if(payment!=null)
                    {
                        listPayments.add(payment)
                    }
                }
                resultPayments(listPayments)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

    fun fetchMenu(date:String,resultMenu:(DayMenu)->Unit){
        val database = FirebaseDatabase.getInstance().reference
        val menuRef= database.child("creche123/menu/"+date)

        menuRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                var dayMenu: DayMenu
                dayMenu= p0.getValue(DayMenu::class.java)!!
                if (dayMenu != null) {
                    resultMenu(dayMenu)
                }
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

    fun fetchHomeworks(kid:KidAccount,resultHomeworks:(List<Other>)->Unit){
        val database = FirebaseDatabase.getInstance().reference
        val homeworkRef= database.child("creche123/sections/${kid.section}/groups/${kid.group}/other")

        homeworkRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val listHomeworks= mutableListOf<Other>()
                p0.children.forEach{
                    val homewrk=it.getValue(Other::class.java)
                    if(homewrk != null)
                    {
                        homewrk.to.forEach {
                            Log.d("firebase", "==============>${kid.kidProfile!!.id}   ==??    ${it}")
                            if(it==kid.kidProfile.id)
                                listHomeworks.add(homewrk)
                        }
                    }
                }
                resultHomeworks(listHomeworks)
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

    fun sendUserMessagingTokenToServer(token: String) {
        val currentKidId = MainActivity.currentKid.kidProfile?.id
        val tokenRef = database.child("creche123/accounts/kidsAccounts/$currentKidId/parent/messagingToken")
        tokenRef.setValue(token)  // add listeners later
    }

}