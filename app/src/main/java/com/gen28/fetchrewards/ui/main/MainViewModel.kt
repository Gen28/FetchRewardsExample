package com.gen28.fetchrewards.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gen28.fetchrewards.ui.main.datasource.RewardDataSource
import com.gen28.fetchrewards.ui.main.entity.Reward
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel : ViewModel() {
    private val _rewards = MutableLiveData<List<Reward>>()
    val rewards: LiveData<List<Reward>> = _rewards

    fun fetchRewards() {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .build()
        val rewardsApi = retrofit.create(RewardDataSource::class.java)

        rewardsApi.getRewards()?.enqueue(object : Callback<List<Reward>> {
            override fun onResponse(call: Call<List<Reward>>, response: Response<List<Reward>>) {
                Log.d(LOG_TAG, "Call: ${response.errorBody()?.string()}")
                response.body()?.let { rewards ->
                    _rewards.postValue(rewards.filter { reward -> reward.name.isNullOrBlank().not() }.sortedByDescending { reward -> reward.name }.sortedByDescending { reward -> reward.listId }.asReversed())
                } ?: Log.e(LOG_TAG,"No list to load")
            }

            override fun onFailure(call: Call<List<Reward>>, t: Throwable) {
                Log.e(LOG_TAG, t.message ?: "onFailure")
            }
        })
    }

    companion object {
        private val LOG_TAG = "MainViewModel"
        private val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }
}