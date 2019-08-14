package com.edsusantoo.bismillah.academy.ui.home

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.edsusantoo.bismillah.academy.R
import com.edsusantoo.bismillah.academy.ui.academy.AcademyFragment
import com.edsusantoo.bismillah.academy.ui.bookmark.BookmarkFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {
    private val SELECTED_MENU = "selected_menu"

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.action_home -> {
                fragment = AcademyFragment.newInstance()
            }
            R.id.action_bookmark -> {
                fragment = BookmarkFragment.newInstance()
            }
        }

        if (fragment != null) {
            supportFragmentManager
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.container, fragment)
                    .commit()
        }

        return@OnNavigationItemSelectedListener true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        nav_view.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            savedInstanceState.getInt(SELECTED_MENU)
        } else {
            nav_view.selectedItemId = R.id.action_home
        }
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt(SELECTED_MENU, nav_view.selectedItemId)
    }
}
