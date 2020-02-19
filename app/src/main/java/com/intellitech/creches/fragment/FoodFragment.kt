package com.intellitech.creches.fragment

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.intellitech.creches.MainActivity
import com.intellitech.creches.R
import com.intellitech.creches.items.MenuItem
import com.intellitech.creches.models.DayMenu
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FoodFragment : Fragment() {
    lateinit var calendar_btn: Button
    lateinit var textview_day: TextView
    lateinit var menu_rv: RecyclerView
    val menuAdapter = GroupAdapter<GroupieViewHolder>()


    //-------------------------------
    private var notificationManager: NotificationManager? = null
    //--------------------------------------------------

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_food, container, false)

        calendar_btn=v.findViewById(R.id.calendar_btn)
        textview_day=v.findViewById(R.id.textview_day)
        menu_rv=v.findViewById(R.id.menu_rv)
        menu_rv.adapter = menuAdapter
        val cal = Calendar.getInstance()
        val myFormat = "EEEE dd MMM yy"

        // create an OnDateSetListener
        // buttonDayteListener(cal,myFormat)
        sendNotification()
        today(myFormat)
        fetchMenu("Monday")
        // create an OnDateSetListener
        buttonDayteListener(cal,myFormat)
        return v
    }
    private fun buttonDayteListener(
        cal: Calendar,
        myFormat: String
    ) {
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
                val date = Date(year, monthOfYear, dayOfMonth - 1)
                val dayString = simpleDateFormat.format(date)
                fetchMenu(dayString)
                sendNotification()
                updateDateInView(cal,myFormat)

            }
        }
        calendar_btn.setOnClickListener {
            DatePickerDialog(
                context,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun today(myFormat: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(myFormat, Locale.ENGLISH)
            //Toast.makeText(context,current.format(formatter),Toast.LENGTH_SHORT).show()
            textview_day.text =  current.format(formatter)
            fetchMenu(current.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)))

        } else {
            val date = Date()
            val formatter = SimpleDateFormat(myFormat)
            Toast.makeText(context,formatter.format(date), Toast.LENGTH_SHORT).show()
            textview_day.text  = formatter.format(date)
            fetchMenu(SimpleDateFormat("EEEE", Locale.ENGLISH).format(date))

        }


    }
    private fun updateDateInView(cal: Calendar, myFormat: String) {
        // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRENCH)
        textview_day.text = sdf.format(cal.time)
    }

    //
    private fun fetchMenu(date:String){
        val database = FirebaseDatabase.getInstance().reference
        val menuRef= database.child("creche123/menu/"+date)

        menuRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter= GroupAdapter<GroupieViewHolder>()
                val todaymenu=p0.getValue(DayMenu::class.java)
                if (todaymenu != null) {
                    adapter.add(MenuItem(todaymenu))

                }
                menu_rv.adapter=adapter
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

    fun sendNotification(){
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val fullScreenIntent = Intent(context, MainActivity::class.java)
        val fullScreenPendingIntent = PendingIntent.getActivity(context, 0,
            fullScreenIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        var builder = NotificationCompat.Builder(this!!.context!!, "0")
            .setSmallIcon(R.drawable.ic_burger)
            .setContentTitle("مرحبا")
            .setContentText("معكم تطبيقنا الجديد")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setFullScreenIntent(fullScreenPendingIntent, true)
        with(NotificationManagerCompat.from(this!!.context!!)) {
            // notificationId is a unique int for each notification that you must define
            notify(0, builder.build())
        }
    }


}
