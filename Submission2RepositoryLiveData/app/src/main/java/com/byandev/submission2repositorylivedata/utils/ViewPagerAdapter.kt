package com.byandev.submission2repositorylivedata.utils

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.ui.fragments.MovieFragment
import com.byandev.submission2repositorylivedata.ui.fragments.TvFragment

class ViewPagerAdapter(
    private val context: Context,
    fm : FragmentManager
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val title = intArrayOf(R.string.movie, R.string.tv_show)

    override fun getCount(): Int = title.size

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(title[position])
    }

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = MovieFragment()
            1 -> fragment = TvFragment()
        }
        return fragment as Fragment
    }

}