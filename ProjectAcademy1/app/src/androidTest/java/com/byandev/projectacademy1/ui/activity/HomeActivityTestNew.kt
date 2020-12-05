@file:Suppress("DEPRECATION")

package com.byandev.projectacademy1.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.ui.home.HomeActivity
import com.byandev.projectacademy1.utils.DataDummy
import com.byandev.projectacademy1.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTestNew {
    /*
    Scenario Instrument Test / UI Test
    1. Menampilkan data kursus academy
        - Memastikan rv_academy dalam keadaan tampil.
        - Gulir rv_academy ke posisi data terakhir.
    2. Menampilkan data detail kursus academy
        - Memberi tindakan klik pada data pertama di rv_academy.
        - Memastikan TextView untuk title tampil sesuai dengan yang diharapkan.
        - Memastikan TextView untuk deadline tampil sesuai dengan yang diharapkan.
    3. Menampilkan data modul
        - Memberi tindakan klik pada data pertama di rv_academy.
        - Memberi tindakan klik pada btn_start.
        - Memastikan rv_module dalam keadaan tampil.
    4. Menampilkan data kursus academy
        - Memberi tindakan klik pada data pertama di rv_academy.
        - Memberi tindakan klik pada btn_start.
        - Memberi tindakan klik pada data pertama di rv_module
        - Memastikan web_view sudah tampil.
    5. Menampilkan data bookmark
        - Klik TabLayout dengan teks Bookmark
        - Memastikan rv_module dalam keadaan tampil.
        - Gulir rv_module ke posisi data terakhir.
     */

    /*
    Tiga Komponen Utama dari Espresso:
    Menggunakan Espresso dalam melakukan Instrumental Testing, berikut adalah 3 komponen utamanya:

        1. ViewMatchers (onView(ViewMatcher)): untuk menemukan elemen atau komponen antarmuka yang diuji.

        2. ViewActions (perform(ViewAction)): untuk memberikan event untuk melakukan sebuah aksi pada komponen antarmuka yang diuji.

        3. ViewAssertions: sebuah kondisi atau state dari komponen yang diuji.
     */

    // data dummy for test
    private val dummyCourse = DataDummy.generateDummyCourse()

    // Annotation Rule digunakan untuk memanggil activity mana yang akan di-launch atau diluncurkan.
    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    /*
    Jika diperhartikan, di sini terdapat kode RecyclerViewActions yang digunakan untuk pengujian
    pada RecyclerView seperti scroll ke posisi tertentu dan klik pada posisi tertentu.
    Kode ini merupakan hasil dari penambahan library :
    (androidTestImplementation "androidx.test.espresso:espresso-contrib:$espressoVersion")
     */
    @Test
    fun loadCourse() {
        // test for load data with espresso
        onView(withId(R.id.rv_academy))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_academy))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))

    }

    @Test
    fun loadDetailCourse() {
        // perform click terhadap item recyclerView click()
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        // check apakah text tampil dengan baik dengan isDisplay()
        onView(withId(R.id.text_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.text_date))
            .check(matches(isDisplayed()))
        // check apakah text yang tampil sesuai dengan data yang ada
        onView(withId(R.id.text_title))
            .check(matches(withText(dummyCourse[0].title)))
        onView(withId(R.id.text_date))
            .check(matches(withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.btn_start)).perform(click())
        onView(withId(R.id.rv_module))
            .check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailModule() {
        onView(withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.btn_start)).perform(click())
        onView(withId(R.id.rv_module)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        onView(withId(R.id.web_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun loadBookmarks() {
        onView(withId(R.id.rv_academy)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("Bookmark")).perform(click())
        onView(withId(R.id.rv_bookmark)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_bookmark)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_date)).check(matches(isDisplayed()))
        onView(withId(R.id.action_bookmark)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    private fun delay2seconds() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}