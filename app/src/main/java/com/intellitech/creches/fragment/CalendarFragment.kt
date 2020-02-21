package com.intellitech.creches.fragment


import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.intellitech.creches.R
import com.intellitech.creches.items.CalendarDayItem
import com.intellitech.creches.items.SessionItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_calendar.*

private const val ARG_KID = "kid"
private const val ARG_KIDS = "kids"


class CalendarFragment : Fragment() {
    private var currentkid: KidAccount? = null
    private var kids: List<KidAccount>? = null
    private var currentDay: String = ""
    val daysAdapter = GroupAdapter<GroupieViewHolder>()
    val sessionsAdapter = GroupAdapter<GroupieViewHolder>()
    private val days: Map<String, String> = mapOf("1" to "Dimanche", "2" to "Lundi","3" to "Mardi","4" to "Mercredi","5" to "Jeudi")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentkid = it.getParcelable(ARG_KID)
            kids = it.getParcelableArrayList(ARG_KIDS)
        }
        Log.d("zaza", kids?.size.toString())
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
            currentDay = dayItem.dayName
            getDayCalendar(dayItem.dayName)
        }

        change_kid_btn.setOnClickListener {
            showKidsDialog()
        }
    }

    private fun getDayCalendar(day: String) {
        sessionsAdapter.clear()
        DataService.getDayCalendar(day, currentkid!!) { sessions ->
            sessions.forEach { session ->
                sessionsAdapter.add(SessionItem(session))
            }
        }
    }

    fun showKidsDialog() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Choose a kid")
        val dialogKids = arrayOfNulls<String>(kids!!.size)
        var i = 0
        kids!!.forEach {
            dialogKids[i] = it.kidProfile?.name + it.kidProfile?.lastName
            i++
        }
        builder.setItems(dialogKids) { dialog, which ->
            when (which) {
                0 -> {
                    currentkid = kids!![0]
                    getDayCalendar(currentDay)
                }
                1 -> {
                    currentkid = kids!![1]
                    getDayCalendar(currentDay)
                }
                2 -> {
                    currentkid = kids!![2]
                    getDayCalendar(currentDay)
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        @JvmStatic
        fun newInstance(kidParam: KidAccount, kidsParam: ArrayList<KidAccount>) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KID, kidParam)
                    putParcelableArrayList(ARG_KIDS, kidsParam as ArrayList<out Parcelable>?)
                }
            }
    }
}
