package com.intellitech.creches

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
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.nav_header.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var mAuth: FirebaseAuth
    lateinit var phone: String
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var kids: List<KidAccount>
    lateinit var currentKid: KidAccount

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, NewsFragment.newInstance(ArrayList(kids))).commit()
            }
            R.id.nav_events -> {
            }
            R.id.nav_food -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, FoodFragment()).commit()
            }
            R.id.nav_homework -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, HomeworkFragment.newInstance(currentKid, ArrayList(kids))).commit()
            }
            R.id.nav_tuitions -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, TuitionsFragment.newInstance(currentKid, ArrayList(kids))).commit()
            }
            R.id.nav_callendar -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.content_frame, CalendarFragment.newInstance(currentKid, ArrayList(kids))).commit()
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

        val firstFragment = NewsFragment()
        val transaction = supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.content_frame, firstFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit()
    }

    override fun onStart() {
        super.onStart()
        DataService.getParentKids(phone) {  kidsResult ->
            kids = kidsResult
            currentKid = kidsResult[0]
            Glide.with(this).load(R.drawable.baby).circleCrop().into(nav_profile)
            studentName.text=currentKid.kidProfile!!.lastName
            studentClass.text="Section:"+currentKid.section+" Groupe:"+currentKid.group+"."
        }
    }
}
