package com.byandev.projectacademy1.ui.viewModels

import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class BookmarkViewModelTest {

    /*
    Scenario Test
    BookmarkViewModelTest:
        Memuat Bookmarks:
            - Memastikan data course tidak null.
            - Memastikan jumlah data course sesuai dengan yang diharapkan.
     */

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel()
    }

    // test true
    @Test
    fun getBookmark() {
        val courseEntities = viewModel.getBookmark()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size) // expected data size
    }


}