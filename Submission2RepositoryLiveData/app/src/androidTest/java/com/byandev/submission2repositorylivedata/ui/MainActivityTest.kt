@file:Suppress("DEPRECATION")

package com.byandev.submission2repositorylivedata.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.utils.EspressoIdlingResource
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun movieTest() {
        onView(withId(R.id.tabs)).check(matches(isDisplayed()))
        onView(withId(R.id.rvListMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvListMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // detail
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tvJudul)).check(matches(isDisplayed()))
        onView(withId(R.id.tvVote)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.rvListGenre)).check(matches(not(isDisplayed())))
        onView(withId(R.id.extended_fab)).check(matches(isDisplayed()))
        onView(withId(R.id.extended_fab)).perform(click())
        pressBack()
    }

    @Test
    fun tvShowTest() {
        onView(withText(R.string.tv_show)).perform(click())
        onView(withId(R.id.rvList)).check(matches(isDisplayed()))
        onView(withId(R.id.rvList)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        // detail
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.app_bar_image)).check(matches(isDisplayed()))
        onView(withId(R.id.tvJudul)).check(matches(isDisplayed()))
        onView(withId(R.id.tvVote)).check(matches(isDisplayed()))
        onView(withId(R.id.tvDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.rvListGenre)).check(matches(not(isDisplayed())))
        pressBack()
    }

}