package com.intellitech.creches.items

import com.intellitech.creches.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.calendar_day_item.view.*

class CalendarDayItem(val dayNum : String, val dayName: String) : Item() {
    override fun getLayout() = R.layout.calendar_day_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.dayNum.text = dayNum
        viewHolder.itemView.dayName.text = dayName
    }
}