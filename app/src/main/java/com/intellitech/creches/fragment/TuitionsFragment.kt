package com.intellitech.creches.fragment

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.items.PaymentItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_tuitions.*

private const val ARG_KID = "kid"
private const val ARG_KIDS = "kids"
class TuitionsFragment : Fragment() {

    private var kid:KidAccount? = null
    val tuitionsAdapter = GroupAdapter<GroupieViewHolder>()

    private var currentkid: KidAccount? = null
    private var kids: List<KidAccount>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tuitions, container, false)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentkid = it.getParcelable(ARG_KID)
            kids = it.getParcelableArrayList(ARG_KIDS)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tuitions_rv.adapter = tuitionsAdapter
        fetchTuitions()
        change_kid_btn.setOnClickListener {
            showKidsDialog()
        }
    }

    fun showKidsDialog() {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Choose a kid")
        val dialogKids = arrayOfNulls<String>(kids!!.size)
        var i = 0
        kids!!.forEach {
            dialogKids[i] = it.kidProfile?.name + it.kidProfile?.lastName
            i++
        }
        builder.setItems(dialogKids) { dialog, which ->
            when (which) {
                0 -> {
                    currentkid = kids!![0]
                    fetchTuitions()
                }
                1 -> {
                    currentkid = kids!![1]
                    fetchTuitions()
                }
                2 -> {
                    currentkid = kids!![2]
                    fetchTuitions()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun fetchTuitions(){
        tuitionsAdapter.clear()
        DataService.fetchTuitions(currentkid!!){ payments->
            payments.forEach { payment ->
                tuitionsAdapter.add(PaymentItem(payment))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(kidParam: KidAccount, kidsParam: ArrayList<KidAccount>) =
            TuitionsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KID, kidParam)
                    putParcelableArrayList(ARG_KIDS, kidsParam as ArrayList<out Parcelable>?)
                }
            }
    }
}
