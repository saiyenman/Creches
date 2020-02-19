package com.intellitech.creches.items

import android.graphics.Color
import com.intellitech.creches.R
import com.intellitech.creches.models.Session
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.session_item.view.*

class SessionItem(private val session: Session) : Item() {

    override fun getLayout() = R.layout.session_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.session_time_tv.text = session.sessionStartTime
        viewHolder.itemView.session_title_tv.text = session.sessionTitle
        // Generating divider color randomly
        val colorCode = (1..3).shuffled().first()
        when(colorCode) {
            1 -> viewHolder.itemView.divider.setBackgroundColor(Color.BLUE)
            2 -> viewHolder.itemView.divider.setBackgroundColor(Color.RED)
            3 -> viewHolder.itemView.divider.setBackgroundColor(Color.YELLOW)
        }
    }
}