package com.intellitech.creches.items

import com.intellitech.creches.R
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.event_image_item.view.*

class EventImageItem(private val imageLink: String) : Item() {

    override fun getLayout() = R.layout.event_image_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        Picasso.get().load(imageLink).into(viewHolder.itemView.event_image_item_picture)
    }
}