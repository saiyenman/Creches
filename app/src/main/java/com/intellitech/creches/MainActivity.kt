package com.intellitech.creches

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
import android.os.Build
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.event_item.view.*
import kotlinx.android.synthetic.main.nav_header.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mAuth: FirebaseAuth
    lateinit var phone: String
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var kids: List<KidAccount>
    lateinit var currentKid: KidAccount
    private val fragmentManager = supportFragmentManager
    // Fragments
    private var newsFragment: Fragment? = null
    private var foodFragment: Fragment? = null
    private var homeworkFragment: Fragment? = null
    private var tuitionsFragment: Fragment? = null
    private var calendarFragment: Fragment? = null
    private var currentFragment: Fragment? = newsFragment

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            val channelId = getString(R.string.default_notification_channel_id)
            val channelName = "parent"
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW))
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(newsFragment!!).commit()
                currentFragment = newsFragment
            }
            R.id.nav_events -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(newsFragment!!).commit()
                currentFragment = newsFragment
            }
            R.id.nav_food -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(foodFragment!!).commit()
                currentFragment = foodFragment
            }
            R.id.nav_homework -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(homeworkFragment!!).commit()
                currentFragment = homeworkFragment
            }
            R.id.nav_tuitions -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(tuitionsFragment!!).commit()
                currentFragment = tuitionsFragment
            }
            R.id.nav_callendar -> {
                fragmentManager.beginTransaction().hide(currentFragment!!).show(calendarFragment!!).commit()
                currentFragment = calendarFragment
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kids = listOf()
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
        createNotificationChannel()

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
    }

    override fun onStart() {
        super.onStart()
        DataService.getParentKids(phone) {  kidsResult ->
            kids = kidsResult
            currentKid = kidsResult[0]
            //  first fragment to launch -> News Fragment
            newsFragment = NewsFragment.newInstance(ArrayList(kids))
            foodFragment = FoodFragment()
            homeworkFragment = HomeworkFragment.newInstance(currentKid, ArrayList(kids))
            tuitionsFragment = TuitionsFragment.newInstance(currentKid, ArrayList(kids))
            calendarFragment = CalendarFragment.newInstance(currentKid, ArrayList(kids))
            Glide.with(this).load(R.drawable.baby).circleCrop().into(nav_profile)
            studentName.text=currentKid.kidProfile!!.lastName
            studentClass.text="Section:"+currentKid.section+" Groupe:"+currentKid.group+"."

            initContent()
        }
    }



    private fun initContent() {
        fragmentManager.beginTransaction().add(R.id.content_frame, newsFragment!!, "news").commit()
        fragmentManager.beginTransaction().add(R.id.content_frame, foodFragment!!, "food").hide(foodFragment!!).commit()
        fragmentManager.beginTransaction().add(R.id.content_frame, homeworkFragment!!, "homework").hide(homeworkFragment!!).commit()
        fragmentManager.beginTransaction().add(R.id.content_frame, tuitionsFragment!!, "tuition").hide(tuitionsFragment!!).commit()
        fragmentManager.beginTransaction().add(R.id.content_frame, calendarFragment!!, "calendar").hide(calendarFragment!!).commit()
        currentFragment = newsFragment
    }
}
