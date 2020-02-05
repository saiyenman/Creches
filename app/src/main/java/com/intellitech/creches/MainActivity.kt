package com.intellitech.creches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.GridLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.intellitech.creches.fragment.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var grid: GridLayout
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

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        navView.setItemIconTintList(null)

        //  fragments
        val firstFragment = NewsFragment()
        firstFragment.arguments = intent.extras
        val transaction = supportFragmentManager.beginTransaction().apply {
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            replace(R.id.content_frame, firstFragment)
            addToBackStack(null)
        }
        // Commit the transaction
        transaction.commit()

    }

}
