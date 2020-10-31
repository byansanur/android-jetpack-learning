package com.byandev.latihanlivedatawithapi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback

class MainViewModel : ViewModel() {

    /*
    1. MutableLiveData bisa kita ubah value-nya
    2. LiveData bersifat read-only (tidak dapat diubah)

    - setValue = Menetapkan sebuah nilai dari LiveData. Jika ada observer yang aktif, nilai akan dikirim kepada mereka. Metode ini harus dipanggil dari main thread.
    - postValue = Posting tugas ke main thread untuk menetapkan nilai yang diberikan dari background thread. Jika Anda memanggil metode ini beberapa kali sebelum main thread menjalankan tugas yang di-posting, hanya nilai terakhir yang akan dikirim.
    - getValue = Mendapatkan nilai dari sebuah LiveData.
     */

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant : LiveData<Restaurant> = _restaurant

    /*
    Inilah yang disebut dengan encapsulation pada LiveData,
    yaitu dengan membuat data yang bertipe MutableLiveData menjadi private (_listReview) dan
    yang bertipe LiveData menjadi public (listReview). Cara ini disebut dengan backing property.
    Dengan begitu Anda dapat mencegah variabel yang bertipe MutableLiveData diubah dari luar class.
    Karena memang seharusnya hanya ViewModel-lah yang dapat mengubah data.
     */
    private val _listReview = MutableLiveData<List<ConsumerReviewsItem>>()
    val listReview: LiveData<List<ConsumerReviewsItem>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "mainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }

    fun findRestaurant() {
        _isLoading.value = true
        val client = ApiConfig.getApiService()
        client.getRestaurant(RESTAURANT_ID).enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant
                    _listReview.value = response.body()?.restaurant?.consumerReviews
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun postReview(review : String) {
        _isLoading.value = false
        val client = ApiConfig.getApiService()
        client.postReview(
            RESTAURANT_ID,
            "Dicoding",
            review)
            .enqueue(object : Callback<PostReviewResponse> {
                override fun onResponse(
                    call: Call<PostReviewResponse>,
                    response: retrofit2.Response<PostReviewResponse>
                ) {
                    _isLoading.value = false
                    if (response.isSuccessful) {
                        _listReview.value = response.body()?.consumerReviews
                    } else {
                        Log.d(TAG, "onResponse: postReview ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<PostReviewResponse>, t: Throwable) {
                    _isLoading.value = false
                    Log.d(TAG, "onFailure: postReview ${t.message.toString()}")
                }

            })
    }
}