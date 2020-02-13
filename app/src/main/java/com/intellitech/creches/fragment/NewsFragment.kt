package com.intellitech.creches.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.intellitech.creches.R
import com.intellitech.creches.interfaces.FirebaseDataInterface
import com.intellitech.creches.items.EventItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_news.*

private const val ARG_PHONE = "phone"

class NewsFragment : Fragment() {
    private var phone: String? = null
    lateinit var parentKids: List<KidAccount>
    private val eventAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val V =inflater.inflate(R.layout.fragment_news, container, false)
        // Inflate the layout for this fragment
        return V
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            phone = it.getString(ARG_PHONE)
        }
        /*DataService.getParentKids(phone!!) {
        }*/
    }

    override fun onResume() {
        super.onResume()
        news_rv.adapter = eventAdapter
        DataService.getKidsGroupEvents(phone!!) { eventList ->
            eventList.forEach { event ->
                eventAdapter.add(EventItem(event))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(phoneParam: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PHONE, phoneParam)
                }
            }
    }

}
