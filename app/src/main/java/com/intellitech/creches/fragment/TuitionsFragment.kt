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
import com.intellitech.creches.items.PaymentItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.models.Payment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

private const val ARG_KID = "kid"
class TuitionsFragment : Fragment() {

    private var kid:KidAccount?=null
    lateinit var tuitions_rv: RecyclerView
    val tuitionsAdapter = GroupAdapter<GroupieViewHolder>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_tuitions, container, false)

        tuitions_rv=v.findViewById(R.id.tuitions_rv)
        tuitions_rv.adapter = tuitionsAdapter
        return v

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            kid = it.getParcelable(ARG_KID)
        }


    }


    private fun fetchTuitions(kid:KidAccount){
        val database = FirebaseDatabase.getInstance().reference
        val tuitionsRef= database.child("creche123/accounts/kidsAccounts/"+kid.kidProfile!!.name+kid.kidProfile.lastName+"/payments")

        tuitionsRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val adapter=GroupAdapter<GroupieViewHolder>()
                p0.children.forEach{
                    val payment=it.getValue(Payment::class.java)
                    if(payment!=null)
                    {
                            adapter.add(PaymentItem(payment))
                    }
                }
                tuitions_rv.adapter=adapter
            }
            override fun onCancelled(p0: DatabaseError) {
                Log.d("firebase", p0.message)
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(kidParam: KidAccount) =
            TuitionsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KID, kidParam)
                }
            }
    }
}
