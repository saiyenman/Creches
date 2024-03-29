package com.intellitech.creches.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.intellitech.creches.R
import com.intellitech.creches.interfaces.FirebaseDataInterface
import com.intellitech.creches.items.EventItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.event_item.*
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {
    private val eventAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v =inflater.inflate(R.layout.fragment_news, container, false)
        // Inflate the layout for this fragment
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_rv.adapter = eventAdapter
        DataService.getKindergartenEvents { events ->
            events.forEach { event ->
                eventAdapter.add(EventItem(event))
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        eventAdapter.clear()
    }

}
