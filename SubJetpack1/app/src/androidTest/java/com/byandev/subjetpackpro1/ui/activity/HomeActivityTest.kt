@file:Suppress("DEPRECATION")
package com.byandev.subjetpackpro1.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.ui.viewModels.MoviesViewModel
import com.byandev.subjetpackpro1.ui.viewModels.TvShowViewModel
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

    private val dummyMovieEntity = MoviesViewModel().getMovie()
    private val dummyTvShowEntity = TvShowViewModel().getTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)


    @Test
    fun loadMovie() {
        if (getRvCount() == 0) {
            onView(ViewMatchers.withId(R.id.progress_bar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } else {
            onView(ViewMatchers.withId(R.id.rvMovie))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(ViewMatchers.withId(R.id.rvMovie))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieEntity.size))
        }

    }

    @Test
    fun loadTvShow() {
        onView(ViewMatchers.withText(R.string.tv_show)).perform(click())

        if (getRvCount() == 0) {
            onView(ViewMatchers.withId(R.id.progress_bar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } else {
            onView(ViewMatchers.withId(R.id.rvTvShow))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(ViewMatchers.withId(R.id.rvTvShow))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowEntity.size))
        }
    }

    @Test
    fun loadDetailMovie() {
        onView(ViewMatchers.withId(R.id.rvMovie))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(ViewMatchers.withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvRelease)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvDesc)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.app_bar_image)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.extended_fab)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.include_layout)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(R.id.tvTitle)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyMovieEntity[0].title
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvRelease)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyMovieEntity[0].releaseDate
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvGenre)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyMovieEntity[0].genre
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvDesc)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyMovieEntity[0].description
                )
            )
        )

        onView(ViewMatchers.withId(R.id.tvDesc)).perform(click())
        onView(ViewMatchers.withId(R.id.extended_fab)).perform(click())
        pressBack()

    }

    @Test
    fun loadDetailTvShow() {
        onView(ViewMatchers.withText(R.string.tv_show)).perform(click())

        onView(ViewMatchers.withId(R.id.rvTvShow))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        onView(ViewMatchers.withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvRelease)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvGenre)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.tvDesc)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.app_bar_image)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.extended_fab)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.toolbar)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        onView(ViewMatchers.withId(R.id.tvTitle)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyTvShowEntity[0].title
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvRelease)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyTvShowEntity[0].releaseDate
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvGenre)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyTvShowEntity[0].genre
                )
            )
        )
        onView(ViewMatchers.withId(R.id.tvDesc)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    dummyTvShowEntity[0].description
                )
            )
        )

        onView(ViewMatchers.withId(R.id.tvDesc)).perform(click())
        onView(ViewMatchers.withId(R.id.extended_fab)).perform(click())
        pressBack()

    }

    private fun getRvCount(): Int {
        val recyclerView = activityRule.activity.findViewById(R.id.rvMovie) as RecyclerView
        return recyclerView.adapter?.itemCount!!
    }
}