@file:Suppress("DEPRECATION")

package com.byandev.projectacademy1.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.byandev.projectacademy1.R
import com.byandev.projectacademy1.ui.home.HomeActivity
import com.byandev.projectacademy1.utils.DataDummy
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

    /*
    Jika diperhartikan, di sini terdapat kode RecyclerViewActions yang digunakan untuk pengujian
    pada RecyclerView seperti scroll ke posisi tertentu dan klik pada posisi tertentu.
    Kode ini merupakan hasil dari penambahan library :
    (androidTestImplementation "androidx.test.espresso:espresso-contrib:$espressoVersion")
     */
    @Test
    fun loadCourse() {
        delay2seconds()
        // test for load data with espresso
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))

    }

    @Test
    fun loadDetailCourse() {
        delay2seconds()
        // perform click terhadap item recyclerView click()
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        // check apakah text tampil dengan baik dengan isDisplay()
        Espresso.onView(ViewMatchers.withId(R.id.text_title))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.text_date))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        // check apakah text yang tampil sesuai dengan data yang ada
        Espresso.onView(ViewMatchers.withId(R.id.text_title))
            .check(ViewAssertions.matches(ViewMatchers.withText(dummyCourse[0].title)))
        Espresso.onView(ViewMatchers.withId(R.id.text_date))
            .check(ViewAssertions.matches(ViewMatchers.withText("Deadline ${dummyCourse[0].deadline}")))
    }

    @Test
    fun loadModule() {
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.btn_start)).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.rv_module))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadDetailModule() {
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.rv_academy)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.btn_start)).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.rv_module)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                ViewActions.click()
            ))
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.web_view))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun loadBookmarks() {
        Espresso.onView(ViewMatchers.withText(R.string.bookmark)).perform(ViewActions.click())
        delay2seconds()
        Espresso.onView(ViewMatchers.withId(R.id.rv_bookmark))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.rv_bookmark))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourse.size))
    }

    private fun delay2seconds() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}