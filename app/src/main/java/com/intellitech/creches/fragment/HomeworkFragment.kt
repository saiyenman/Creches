package com.intellitech.creches.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.intellitech.creches.R
import com.intellitech.creches.items.HomeworkItem
import com.intellitech.creches.models.Other
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class HomeworkFragment : Fragment() {
    private var kid:String?=null
    lateinit var homework_rv:RecyclerView
    val homeworkAdapter = GroupAdapter<GroupieViewHolder>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment_homework, container, false)
        homework_rv=v.findViewById(R.id.homework_rv)
        homework_rv.adapter = homeworkAdapter

/*        val event = DataService.getSingleEvent(object : FirebaseDataInterface {
            override fun onHomeworkDataFetched(homework: Other) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onEventDataFetched(event: Event) {
                homeworkAdapter.add(EventItem(event))
                Log.d("firenase", event.eventDescription)
            }
        })*/
        fetchHomworks()
        return v
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            kid = it.getString("kidid")
        }


    }
    private fun fetchHomworks(){
        val database = FirebaseDatabase.getInstance().reference
        val homeworkRef= database.child("creche123/sections/0/groups/0/other")

        homeworkRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()
                p0.children.forEach{
                    val homewrk=it.getValue(Other::class.java)
                    if(homewrk!=null)
                    {
                        if(homewrk.to.contains("islem")){
                            adapter.add(HomeworkItem(homewrk))
                        }
                    }
                }
                homework_rv.adapter=adapter
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

}
