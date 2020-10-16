package com.byandev.subjetpackpro1.ui.helper

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.ui.fragments.MovieFragment
import com.byandev.subjetpackpro1.ui.fragments.TvShowFragment

class ViewPagerAdapter(
    private val context: Context,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
) {
    companion object {
        @StringRes
        private val TITLE = intArrayOf(R.string.movies, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when(position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? =
        context.getString(TITLE[position])
}