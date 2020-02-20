package com.intellitech.creches.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.items.PaymentItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
private const val ARG_KID = "kid"
class TuitionsFragment : Fragment() {

    private var kid:KidAccount? = null
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

    override fun onStart() {
        super.onStart()
        tuitions_rv.adapter = tuitionsAdapter
        DataService.fetchTuitions(kid!!){ payments->
            payments.forEach { payment ->
                tuitionsAdapter.add(PaymentItem(payment))
            }
        }
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
