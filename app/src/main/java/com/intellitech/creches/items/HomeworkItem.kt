package com.intellitech.creches.items


import android.view.View
import com.intellitech.creches.R
import com.intellitech.creches.models.Other
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.homeworkitem.view.*

class HomeworkItem(private val homework: Other) : Item() {

    override fun getLayout() = R.layout.homeworkitem

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.homework_item_time.text = "Given the "+homework.timing
        viewHolder.itemView.homework_item_title.text=homework.title
        viewHolder.itemView.homework_item_text.text = homework.descritption
        if(homework.done==true) {
            viewHolder.itemView.doneimg.visibility = View.VISIBLE
        }
    }
}