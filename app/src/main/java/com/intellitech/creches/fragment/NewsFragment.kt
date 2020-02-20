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

private const val ARG_KIDS = "phone"

class NewsFragment : Fragment() {
    private var kids: ArrayList<KidAccount>? = null
    private val eventAdapter = GroupAdapter<GroupieViewHolder>()
    lateinit var item_mail_avatar:ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v =inflater.inflate(R.layout.fragment_news, container, false)
        // Inflate the layout for this fragment
        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            kids = it.getParcelableArrayList(ARG_KIDS)
        }
    }

    override fun onStart() {
        super.onStart()
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

    companion object {
        @JvmStatic
        fun newInstance(kidsParam: ArrayList<KidAccount>) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_KIDS, kidsParam as ArrayList<out Parcelable>?)
                }
            }
    }

}
