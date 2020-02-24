package com.intellitech.creches.items

import com.bumptech.glide.Glide
import com.intellitech.creches.R
import com.intellitech.creches.models.Event
import com.intellitech.creches.models.Meal
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.meal_item.view.*

class MealItem (private val meal: Meal) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.meal.text = meal.food
        //val img=meal.img
        Glide.with(viewHolder.root.context).load(R.drawable.icons_cheese).into(viewHolder.itemView.food_item)
    }

    override fun getLayout() = R.layout.meal_item

}