package com.intellitech.creches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class NewsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val V =inflater.inflate(R.layout.fragment_news, container, false)
        // Inflate the layout for this fragment
        return V
    }

}
