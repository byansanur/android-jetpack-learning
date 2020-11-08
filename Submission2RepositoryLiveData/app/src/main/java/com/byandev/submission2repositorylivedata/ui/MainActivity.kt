package com.byandev.submission2repositorylivedata.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapterViewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPagers.adapter = adapterViewPagerAdapter
        tabs.setupWithViewPager(viewPagers)
        supportActionBar?.elevation = 0f
    }
}