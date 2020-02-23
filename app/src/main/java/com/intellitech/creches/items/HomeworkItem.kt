package com.intellitech.creches.items


import android.view.View
import com.intellitech.creches.R
import com.intellitech.creches.models.Other
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.homework_item.view.*

class HomeworkItem(private val homework: Other) : Item() {

    override fun getLayout() = R.layout.homework_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.homework_item_time.text =viewHolder.root.context.getString(R.string.given)+" "+homework.timing
        viewHolder.itemView.homework_item_title.text=homework.title
        viewHolder.itemView.homework_item_text.text = homework.description
        if(homework.done==true) {
            viewHolder.itemView.doneimg.visibility = View.VISIBLE
        }
    }
}