package com.intellitech.creches.items

import android.util.Log
import com.intellitech.creches.R
import com.intellitech.creches.models.Event
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.event_item.view.*
import org.threeten.bp.LocalDate
import org.threeten.bp.Period
import org.threeten.bp.format.DateTimeFormatter
import java.util.*


class EventItem(private val event: Event) : Item() {
    private val imageAdapter = GroupAdapter<GroupieViewHolder>()


    override fun getLayout() = R.layout.event_item

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.event_item_text.text = event.eventDescription
        val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH)
        val date = LocalDate.parse(event.eventDate, formatter)
        val period = Period.between(date, LocalDate.now())
        if (period.days > 1) {
            viewHolder.itemView.event_item_time.text = event.eventDate
        } else {
            viewHolder.itemView.event_item_time.text = event.eventTime
        }

        // Handling the image horizontal recycler
        viewHolder.itemView.event_item_image_recycler.adapter = imageAdapter
        for (image in event.eventPictures) {
            imageAdapter.add(EventImageItem(image))
        }
    }
}