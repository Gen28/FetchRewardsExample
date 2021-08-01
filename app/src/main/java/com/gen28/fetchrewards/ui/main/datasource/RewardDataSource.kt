package com.gen28.fetchrewards.ui.main.datasource

import com.gen28.fetchrewards.ui.main.entity.Reward
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface RewardDataSource {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("hiring.json")
    fun getRewards(): Call<List<Reward>>?
}