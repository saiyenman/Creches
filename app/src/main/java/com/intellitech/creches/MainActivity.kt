package com.intellitech.creches

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.GridLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.intellitech.creches.fragment.*
import com.intellitech.creches.models.KidAccount
import com.intellitech.creches.services.DataService
import com.intellitech.creches.utils.PARENT_PHONE_EXTRA
import com.intellitech.creches.utils.PARENT_PHONE_PREF
import com.intellitech.creches.utils.SHARED_PREF_NAME
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_news.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mAuth: FirebaseAuth
    lateinit var phone: String
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, NewsFragment()).commit()
            }
            R.id.nav_events -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, EventsFragment()).commit()
            }
            R.id.nav_food -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, FoodFragment()).commit()
            }
            R.id.nav_homework -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, HomeworkFragment()).commit()
            }
            R.id.nav_tuitions -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, TuitionsFragment()).commit()
            }
            R.id.nav_callendar -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, CalendarFragment()).commit()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser
        // If user not already logged in -> go to Login Activity
        if (currentUser == null) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        // Getting the phone number from the login activity Or SP if already logged in
        if (intent.getStringExtra(PARENT_PHONE_EXTRA) != null) {
            phone = intent.getStringExtra(PARENT_PHONE_EXTRA)
        } else {
            phone = getSharedPreferences(SHARED_PREF_NAME, 0).getString(PARENT_PHONE_PREF, "")!!
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        navView.itemIconTintList = null

        //  first fragment to launch -> News Fragment
        val newsFragment = NewsFragment.newInstance(phone)
        val transaction = supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_frame, newsFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit()
        //DataService.createDatabase()
        //DataService.updateYear()
    }

    override fun onStart() {
        super.onStart()
        //news_rv.adapter = eventsAdapter
    }

    override fun onResume() {
        super.onResume()
        /*val event = DataService.getSingleEvent(object : FirebaseDataInterface {
            override fun onEventDataFetched(event: Event) {
                eventsAdapter.add(EventItem(event))
                Log.d("firenase", event.eventDescription)
            }
        })*/
    }
}
