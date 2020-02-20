package com.intellitech.creches.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.items.HomeworkItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_calendar.*
private const val ARG_KID = "kid"
private const val ARG_KIDS = "kids"
class HomeworkFragment : Fragment() {
    lateinit var homework_rv:RecyclerView
    val homeworkAdapter = GroupAdapter<GroupieViewHolder>()
    private var currentkid: KidAccount? = null
    private var kids: List<KidAccount>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val v =inflater.inflate(R.layout.fragment_homework, container, false)
        return v
    }

    override fun onStart() {
        super.onStart()
        homework_rv.adapter = homeworkAdapter
        change_kid_btn.setOnClickListener {
            showKidsDialog()
        }
        fetchHomeworks()
    }
    private fun fetchHomeworks(){

        DataService.fetchHomworks(currentkid!!){ homeworks->
            homeworks.forEach { homework ->
                homeworkAdapter.add(HomeworkItem(homework))
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentkid = it.getParcelable(ARG_KID)
            kids = it.getParcelableArrayList(ARG_KIDS)
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
                    fetchHomeworks()
                }
                1 -> {
                    currentkid = kids!![1]
                    fetchHomeworks()
                }
                2 -> {
                    currentkid = kids!![2]
                    fetchHomeworks()
                }
            }
        }
        val dialog = builder.create()
        dialog.show()
    }


    companion object {
        @JvmStatic
        fun newInstance(kidParam: KidAccount, kidsParam: ArrayList<KidAccount>) =
            HomeworkFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_KID, kidParam)
                    putParcelableArrayList(ARG_KIDS, kidsParam as ArrayList<out Parcelable>?)
                }
            }
    }
}
