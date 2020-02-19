package com.intellitech.creches.items

import com.intellitech.creches.R
import com.intellitech.creches.models.DayMenu
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.menuitem.view.*

class MenuItem(private val dayMenu: DayMenu) : Item() {

    override fun getLayout() = R.layout.menuitem

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.menu_item_meal1.text = dayMenu.meal1
        viewHolder.itemView.menu_item_meal2.text = dayMenu.meal2
        viewHolder.itemView.menu_item_meal3.text = dayMenu.meal3

    }
}