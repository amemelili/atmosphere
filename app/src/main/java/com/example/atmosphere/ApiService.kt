package com.example.atmosphere

import com.example.atmosphere.DailyWeather
import retrofit.Call
import retrofit.http.GET

interface ApiService {

    @GET(" ")
    fun fetchRealtime() : Call<RealtimeWeather>

    @GET(" ")
    fun fetchDaily() : Call<DailyWeather>

    @GET(" ")
    fun fetchHourly() : Call<HourlyWeather>
}