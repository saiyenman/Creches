package com.intellitech.creches.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.intellitech.creches.R
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class CalendarFragment : Fragment() {
      lateinit var calendar_btn:Button
      lateinit var textview_day:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_calendar, container, false)
        calendar_btn=v.findViewById(R.id.calendar_btn)
        textview_day=v.findViewById(R.id.textview_day)
        var cal = Calendar.getInstance()
        var myFormat = "EEEE dd MMM yy"

        // create an OnDateSetListener
       // buttonDayteListener(cal,myFormat)

        today(myFormat)
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
                updateDateInView(cal,myFormat)
            }
        }
        calendar_btn!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    context,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }

    private fun today(myFormat: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(myFormat)
            //Toast.makeText(context,current.format(formatter),Toast.LENGTH_SHORT).show()
            textview_day.text =  current.format(formatter)

        } else {
            var date = Date()
            val formatter = SimpleDateFormat(myFormat)
            Toast.makeText(context,formatter.format(date),Toast.LENGTH_SHORT).show()
            textview_day.text  = formatter.format(date)

        }

    }

    private fun updateDateInView(cal: Calendar, myFormat: String) {
        // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRENCH)
        textview_day!!.text = sdf.format(cal.getTime())
    }

}
