package com.intellitech.creches.interfaces

import com.intellitech.creches.models.Event
import com.intellitech.creches.models.KidAccount

interface FirebaseDataInterface {
    fun onEventDataFetched(event: Event)
    fun onParentKidsFetched(kids: List<KidAccount>)
}