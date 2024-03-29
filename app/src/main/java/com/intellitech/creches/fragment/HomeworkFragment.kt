package com.intellitech.creches.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.intellitech.creches.R
import com.intellitech.creches.items.HomeworkItem
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_calendar.*
import kotlinx.android.synthetic.main.fragment_calendar.change_kid_btn
import kotlinx.android.synthetic.main.fragment_homework.*

private const val ARG_KID = "kid"
private const val ARG_KIDS = "kids"
class HomeworkFragment : Fragment() {
    val homeworkAdapter = GroupAdapter<GroupieViewHolder>()
    private var currentkid: KidAccount? = null
    private var kids: List<KidAccount>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homework, container, false)

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
        homework_rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        homework_rv.adapter = homeworkAdapter
        fetchHomeworks()
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

    private fun fetchHomeworks(){

        DataService.fetchHomeworks(currentkid!!){ homeworks->
            homeworks.forEach { homework ->
                Log.d("firebase", homework.title+"===="+homework.description+"===="+homework.timing)
                homeworkAdapter.add(HomeworkItem(homework))
            }
        }
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
