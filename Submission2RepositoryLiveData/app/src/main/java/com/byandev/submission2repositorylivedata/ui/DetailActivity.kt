package com.byandev.submission2repositorylivedata.ui

import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.app.ShareCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.ui.viewModel.MovieViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.TvViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.ViewModelFactory
import com.byandev.submission2repositorylivedata.utils.Constant
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    private lateinit var title: String
    private lateinit var homePageUrl: String
    private val movieViewModel by lazy {
        val viewModelFactory = ViewModelFactory.getInstance()
        ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel::class.java)
    }
    private val tvViewModel by lazy {
        val viewModelFactory = ViewModelFactory.getInstance()
        ViewModelProviders.of(this, viewModelFactory).get(TvViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setScrollAppbar()
        val idMovie = intent.getStringExtra("idMovie")
        val idTvShow = intent.getStringExtra("idTvShow")
        if (idMovie != null) {
            movieViewModel.getMovieDetail(idMovie.toLong()).observe(this, { movie ->
                loadMovieData(movie)
            })
        } else if (idTvShow != null) {
            tvViewModel.getTvDetail(idTvShow.toLong()).observe(this, { tvShow ->
                loadTvShowData(tvShow)
            })
        }


    }

    private fun loadTvShowData(tvShow: TvDetailResponse?) {
        toolbar.title = tvShow?.name
        Glide.with(this)
            .load("${Constant.IMAGE_URL}w342${tvShow?.poster_path}")
            .into(app_bar_image)

        title = tvShow?.name.toString()
        homePageUrl = tvShow?.homepage!!

        extended_fab.setOnClickListener {
            intentShare(title, homePageUrl)
        }
    }

    private fun loadMovieData(movie: MovieDetail?) {
        toolbar.title = movie?.title
        Glide.with(this)
            .load("${Constant.IMAGE_URL}w342${movie?.poster_path}")
            .into(app_bar_image)

        title = movie?.title.toString()
        homePageUrl = movie?.homepage!!

        extended_fab.setOnClickListener {
            intentShare(title, homePageUrl)
        }
    }

    private fun intentShare(title:String?, homepage: String?) {
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle("Bagikan film ini kesiapapun")
            .setText("Hei ini ada film bagus ${title}, lihat disini $homepage")
            .startChooser()
    }

    private fun setScrollAppbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appbar.outlineProvider = null
        }
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        appbar.addOnOffsetChangedListener(AppBarLayout.BaseOnOffsetChangedListener { _: AppBarLayout?, verticalOffset: Int ->
            if (collapsing.height+ verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsing)) {
                toolbar.navigationIcon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.argb(100,255,255,255),
                        BlendModeCompat.SRC_ATOP
                    )
                toolbar.setTitleTextColor(this.resources.getColor(R.color.white))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            } else {
                toolbar.navigationIcon?.colorFilter =
                    BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                        Color.argb(100,255,255,255),
                        BlendModeCompat.SRC_ATOP
                    )
                toolbar.setTitleTextColor(this.resources.getColor(R.color.white))
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            }
        } as AppBarLayout.BaseOnOffsetChangedListener<*> )
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.shareMenu) {
            intentShare(title,homePageUrl)
        }
        return super.onOptionsItemSelected(item)
    }
}