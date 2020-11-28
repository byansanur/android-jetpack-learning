package com.byandev.submission2repositorylivedata.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.byandev.submission2repositorylivedata.R
import com.byandev.submission2repositorylivedata.adapter.GenreAdapter
import com.byandev.submission2repositorylivedata.data.repository.remote.GenreDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.MovieDetail
import com.byandev.submission2repositorylivedata.data.repository.remote.TvDetailResponse
import com.byandev.submission2repositorylivedata.ui.viewModel.MovieViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.TvViewModel
import com.byandev.submission2repositorylivedata.ui.viewModel.ViewModelFactory
import com.byandev.submission2repositorylivedata.utils.Constant.Companion.IMAGE_URL
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    private lateinit var adapterGenre: GenreAdapter
    private var genre = listOf<GenreDetail>()
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

    private var skeleton: Skeleton? = null

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

    private fun loadTvShowData(tvShow: TvDetailResponse) {
        extended_fab.visibility = View.GONE
        toolbar.title = tvShow.name
        Glide.with(this)
            .load("${IMAGE_URL}w342${tvShow.backdrop_path}")
            .into(app_bar_image)

        Glide.with(this)
            .load("${IMAGE_URL}w342${tvShow.poster_path}")
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(imgPoster)

        tvJudul.text = tvShow.name
        tvVote.text = tvShow.vote_average.toString()
        tvDesc.text = tvShow.overview
        setRv()
        skeleton = rvListGenre.applySkeleton(R.layout.item_genre, 2)
        skeleton?.showSkeleton()
        tvShow.id.toLong().let {
            skeleton?.showOriginal()
            tvViewModel.getGenresTv(it).observe(this, { list ->
                if (list.isNullOrEmpty()) {
                    Toast.makeText(this, "genre gak ada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "sukses observer", Toast.LENGTH_SHORT).show()
                    genre = list
                    adapterGenre.addList(list)
                }
            })
        }
    }

    private fun loadMovieData(movie: MovieDetail) {
        toolbar.title = movie.title
        Glide.with(this)
            .load("${IMAGE_URL}w342${movie.backdrop_path}")
            .into(app_bar_image)

        Glide.with(this)
            .load("${IMAGE_URL}w342${movie.poster_path}")
            .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
            .into(imgPoster)

        tvJudul.text = movie.title
        title = movie.original_title
        homePageUrl = movie.homepage
        tvVote.text = movie.vote_average.toString()
        tvDesc.text = movie.overview
        setRv()
        skeleton = rvListGenre.applySkeleton(R.layout.item_genre, 2)
        skeleton?.showSkeleton()
        movie.id.toLong().let {
            skeleton?.showOriginal()
            movieViewModel.getGenres(it).observe(this, { list ->
                if (list.isNullOrEmpty()) {
                    Toast.makeText(this, "genre gak ada", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "sukses observer", Toast.LENGTH_SHORT).show()
                    genre = list
                    adapterGenre.addList(list)
                }
            })
        }

        extended_fab.setOnClickListener {
            intentShare(title, homePageUrl)
        }
    }

    private fun setRv() {
        adapterGenre = GenreAdapter()
        adapterGenre.notifyDataSetChanged()
        rvListGenre.apply {
            adapter = adapterGenre
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            appbar.outlineProvider = null
//        }
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_ios_24)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }
}