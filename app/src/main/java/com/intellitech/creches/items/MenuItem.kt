package com.intellitech.creches.items

import com.intellitech.creches.R
import com.intellitech.creches.models.DayMenu
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.menuitem.view.*

class MenuItem(private val dayMenu: DayMenu) : Item() {

    override fun getLayout() = R.layout.menuitem

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.menu_item_breakfast.text = dayMenu.breakfast
        viewHolder.itemView.menu_item_lunch.text = dayMenu.lunch

    }
}