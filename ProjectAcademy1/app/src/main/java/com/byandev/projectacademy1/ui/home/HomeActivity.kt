package com.byandev.projectacademy1.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.ui.helper.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val sectionsPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}