package com.intellitech.creches.items


import com.intellitech.creches.R
import com.intellitech.creches.models.Event
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.event_item.view.*

class EventItem(private val event: Event) : Item() {
    private val imageAdapter = GroupAdapter<GroupieViewHolder>()

    override fun getLayout() = R.layout.event_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.event_item_text.text = event.eventDescription
        viewHolder.itemView.event_item_text.text = event.eventDescription
        // Handling the image horizontal recycler
        viewHolder.itemView.event_item_image_recycler.adapter = imageAdapter
        for (image in event.eventPictures) {
            imageAdapter.add(EventImageItem(image))
        }
    }
}