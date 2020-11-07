package com.byandev.exampleidlingresource

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource())
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource())
    }


    @Test
    fun checkTest() {
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.prepare))))
        onView(withText(mActivityRule.activity.getString(R.string.start))).perform(click())
        onView(withId(R.id.text_view)).check(matches(withText(mActivityRule.activity.getString(R.string.delay2))))


        // note setelah test ini selesai maka hapus idling resource pada main activity karena menyebabkan memory leak
    }



}