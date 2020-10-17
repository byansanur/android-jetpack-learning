package com.byandev.subjetpackpro1.ui.activity

import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.byandev.subjetpackpro1.R
import com.byandev.subjetpackpro1.data.MovieEntity
import com.byandev.subjetpackpro1.data.TvShowEntity
import com.byandev.subjetpackpro1.ui.viewModels.DetailViewModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
    }

    private lateinit var viewModel: DetailViewModel
    private var expandable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extrasMovies = intent.extras
        val extrasTv = intent.extras

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        if (extrasMovies != null) {
            val movieId = extrasMovies.getString(EXTRA_MOVIE)
            prepareDataMovie(movieId)
        }
        if (extrasTv != null) {
            val tvId = extrasTv.getString(EXTRA_TV)
            prepareDataTvShow(tvId)
        }

        setAppBar()

        tvDesc.viewTreeObserver.addOnGlobalLayoutListener {
            if (!expandable) {
                if (tvDesc.lineCount > 3) {
                    tvDesc.visibility = View.VISIBLE
                }
            }
        }

        tvDesc.setOnClickListener { expand() }
    }

    private fun expand() {
        if (!expandable) {
            expandable = true
            tvDesc.maxLines = Int.MAX_VALUE
        } else {
            expandable = false
            tvDesc.maxLines = 3
        }
    }

    @Suppress("DEPRECATION")
    private fun setAppBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            appbar.outlineProvider = null
        }
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        appbar.addOnOffsetChangedListener(BaseOnOffsetChangedListener { _: AppBarLayout?, verticalOffset: Int ->
            if (collapsing.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsing)) {
                toolbar.navigationIcon?.setColorFilter(
                    resources.getColor(R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }
            } else {
                toolbar.navigationIcon?.setColorFilter(
                    resources.getColor(R.color.colorPrimary),
                    PorterDuff.Mode.SRC_ATOP
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                }
            }
        } as BaseOnOffsetChangedListener<*>)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }


    private fun prepareDataMovie(movieId: String?) {
        if (movieId != null) {
            viewModel.setSelectedMovie(movieId)
            val movies = viewModel.getMovie()
            getDataDetailMovies(movies)
        }
    }

    private fun getDataDetailMovies(movies: MovieEntity) {
        Glide.with(this)
            .load(movies.imgPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_signal_cellular_connected_no_internet_4_bar_24)
            )
            .fitCenter()
            .centerCrop()
            .into(app_bar_image)
        tvTitle.text = movies.title
        tvRelease.text = movies.releaseDate
        tvGenre.text = movies.genre
        tvDesc.text = movies.description
    }

    private fun prepareDataTvShow(tvId: String?) {
        if (tvId != null) {
            viewModel.setSelectedTv(tvId)
            val tv = viewModel.getTv()
            getDataDetailTvShow(tv)
        }
    }

    private fun getDataDetailTvShow(tv: TvShowEntity) {
        Glide.with(this)
            .load(tv.imgPath)
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_baseline_broken_image_24)
                    .error(R.drawable.ic_baseline_signal_cellular_connected_no_internet_4_bar_24)
            )
            .fitCenter()
            .centerCrop()
            .into(app_bar_image)
        tvTitle.text = tv.title
        tvRelease.text = tv.releaseDate
        tvGenre.text = tv.genre
        tvDesc.text = tv.description
    }
}