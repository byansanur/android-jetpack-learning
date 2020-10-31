package com.byandev.latihanlivedatawithapi

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        mainViewModel.findRestaurant()
        mainViewModel.restaurant.observe(this, {
            tvTitle.text = it.name
            tvDescription.text = "${it.description.take(100)}..."
            Glide.with(this)
                .load("https://restaurant-api.dicoding.dev/images/large/${it.pictureId}")
                .into(ivPicture)
        })

        mainViewModel.listReview.observe(this, { it ->
            val listReview = ArrayList<String>()
            if (!it.isNullOrEmpty()) {
                it.forEach {
                    listReview.add("${it.review}\n- ${it.name}")
                }
                lvReview.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listReview)
            }
        })
        mainViewModel.isLoading.observe(this, {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        btnSend.setOnClickListener { v ->
            mainViewModel.postReview(edReview.text.toString())
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}