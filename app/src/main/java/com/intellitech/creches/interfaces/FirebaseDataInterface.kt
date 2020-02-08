package com.intellitech.creches.interfaces

import com.intellitech.creches.models.Event

interface FirebaseDataInterface {
    fun onEventDataFetched(event: Event)
}