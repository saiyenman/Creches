package com.intellitech.creches.fragment

import android.app.*
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.intellitech.creches.R
import com.intellitech.creches.items.MealItem
import com.intellitech.creches.services.DataService
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_food.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class FoodFragment : Fragment() {
    val food_Adapter1 = GroupAdapter<GroupieViewHolder>()
    val food_Adapter2 = GroupAdapter<GroupieViewHolder>()
    val food_Adapter3 = GroupAdapter<GroupieViewHolder>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        menu_item_meal1.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        menu_item_meal2.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        menu_item_meal3.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        menu_item_meal1.adapter=food_Adapter1
        menu_item_meal2.adapter=food_Adapter2
        menu_item_meal3.adapter=food_Adapter3
        //textview_day=v.findViewById(R.id.textview_day)
        val cal = Calendar.getInstance()
        val myFormat = "EEEE dd MMM yy"
        // create an OnDateSetListener
        // buttonDayteListener(cal,myFormat)
        today(myFormat)
        // create an OnDateSetListener
        buttonDayteListener(cal,myFormat)

        calendarView.setTopbarVisible(false)
        calendarView.setSelectedDate(CalendarDay.today())
        calendarView.state().edit()
            .setMinimumDate(CalendarDay.from(2019, 9, 1))
            .setMaximumDate(CalendarDay.from(2020, 9, 30))
            .commit()
        calendarView.setOnDateChangedListener(OnDateSelectedListener(){widget: MaterialCalendarView, date:CalendarDay, selected:Boolean->
            val simpleDateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
            //val date = Date(date.year, date.month,  date.day)//-1
            val date2= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalDateTime.of(date.year,date.month,date.day,0,0).format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH))
            } else {
                Date(date.year, date.month,  date.day)//-1
            }
            fetch(date2.toString())
        })
        }
    private fun fetch(date:String){
        food_Adapter1.clear()
        food_Adapter2.clear()
        food_Adapter3.clear()
        DataService.fetchMenu(date){ daymenu->
            daymenu.meal1.forEach { meal ->
                food_Adapter1.add(MealItem(meal))
            }
            daymenu.meal2.forEach { meal ->
                food_Adapter2.add(MealItem(meal))
            }
            daymenu.meal3.forEach { meal ->
                food_Adapter3.add(MealItem(meal))
            }
        }
    }

    private fun today(myFormat: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern(myFormat, Locale.ENGLISH)
            //Toast.makeText(context,current.format(formatter),Toast.LENGTH_SHORT).show()
            //textview_day.text =  current.format(formatter)
            fetch(current.format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH)))

        } else {
            val date = Date()
            val formatter = SimpleDateFormat(myFormat)
            Toast.makeText(context,formatter.format(date), Toast.LENGTH_SHORT).show()
            //textview_day.text  = formatter.format(date)
            fetch(SimpleDateFormat("EEEE", Locale.ENGLISH).format(date))

        }
    }
    private fun buttonDayteListener(
        cal: Calendar,
        myFormat: String
    ){
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val simpleDateFormat = SimpleDateFormat("EEEE", Locale.ENGLISH)
                val date = Date(year, monthOfYear, dayOfMonth - 1)
                val dayString = simpleDateFormat.format(date)
                fetch(dayString)
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





    private fun updateDateInView(cal: Calendar, myFormat: String) {
        // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.FRENCH)
        //textview_day.text = sdf.format(cal.time)
    }


}
