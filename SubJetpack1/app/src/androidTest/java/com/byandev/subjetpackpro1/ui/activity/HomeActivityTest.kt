package com.byandev.subjetpackpro1.ui.activity

import androidx.test.rule.ActivityTestRule
import com.byandev.subjetpackpro1.utils.DataDummy
import org.junit.Rule

@Suppress("DEPRECATION")
class HomeActivityTest {

    private val dummyMovieEntity = DataDummy.generateDataMovie()
    private val dummyTvShowEntity = DataDummy.generateDataTvShow()

    @get:Rule
    var activityRule = ActivityTestRule(HomeActivity::class.java)

    /*
    TODO 1 : test home activity load data in recyclerView

    TODO 2 : Click tabLayout for check data in recyclerView

    TODO 3 : Perform click in item direct to detailActivity

    TODO 4 : Check data in detailActivity

    TODO 5 : Click textView description to check expandable

    TODO 6 : Click extendFloatingActionButton for check share

     */
}