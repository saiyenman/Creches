package com.intellitech.creches.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intellitech.creches.R
import com.intellitech.creches.items.CalendarDayItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_calendar.*

class CalendarFragment : Fragment() {
    val adapter = GroupAdapter<GroupieViewHolder>()
    private val days: Map<String, String> = mapOf("1" to "Dimanche", "2" to "Lundi","3" to "Mardi","4" to "Mercredi","5" to "Jeudi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_calendar, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        days_rv.adapter = adapter
        days.forEach {
            adapter.add(CalendarDayItem(it.key, it.value))
        }
    }

}
