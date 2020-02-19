package com.intellitech.creches

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
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
import android.widget.Toast
import android.content.DialogInterface
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mAuth: FirebaseAuth
    lateinit var phone: String
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var kids: List<KidAccount>
    lateinit var currentKid: KidAccount

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "KidTech Channel"
            val descriptionText = ""
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("0", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                supportFragmentManager.beginTransaction().add(R.id.content_frame, NewsFragment.newInstance(phone)).commit()
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
        createNotificationChannel()
        DataService.getParentKids(phone) {  kidsResult ->
            kids = kidsResult
            currentKid = kidsResult[0]
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
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
    }
}
