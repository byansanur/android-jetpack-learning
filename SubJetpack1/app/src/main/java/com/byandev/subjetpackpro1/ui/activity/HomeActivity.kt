package com.byandev.subjetpackpro1.ui.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.ui.helper.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = viewPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}