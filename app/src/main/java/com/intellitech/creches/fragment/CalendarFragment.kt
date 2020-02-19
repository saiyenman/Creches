package com.intellitech.creches.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.intellitech.creches.R
import com.intellitech.creches.items.CalendarDayItem
import com.intellitech.creches.items.SessionItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.models.Session
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_calendar.*

private const val ARG_KID = "kid"


class CalendarFragment : Fragment() {
    private var kid: KidAccount? = null
    val daysAdapter = GroupAdapter<GroupieViewHolder>()
    val sessionsAdapter = GroupAdapter<GroupieViewHolder>()
    private val days: Map<String, String> = mapOf("1" to "Dimanche", "2" to "Lundi","3" to "Mardi","4" to "Mercredi","5" to "Jeudi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            kid = it.getParcelable(ARG_KID)
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_calendar, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        days_rv.adapter = daysAdapter
        sessions_rv.adapter = sessionsAdapter
        days.forEach {
            daysAdapter.add(CalendarDayItem(it.key, it.value))
        }
        daysAdapter.setOnItemClickListener { item, view ->
            val dayItem = item as CalendarDayItem
            getDayCalendar(dayItem.dayName)
        }
    }

    private fun getDayCalendar(day: String) {
        sessionsAdapter.clear()
        DataService.getDayCalendar(day, kid!!) { sessions ->
            sessions.forEach { session ->
                sessionsAdapter.add(SessionItem(session))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(kidParam: KidAccount) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KID, kidParam)
                }
            }
    }
}
