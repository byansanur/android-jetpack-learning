package com.byandev.submission2repositorylivedata.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.core.Is
import org.junit.Assert

class RecyclerViewItemCount(private val rvExpectation: Int) : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {

        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val rvExpect = view as RecyclerView
        val adapter = rvExpect.adapter
        Assert.assertNotNull(adapter)
        ViewMatchers.assertThat(adapter?.itemCount, Is.`is`(this.rvExpectation))

    }

}