package com.example.atmosphere

import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast

import retrofit.*

class ApiCall {
    fun fetchData(context : Context, callback : (DailyWeather) -> Unit) {

        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/forecast?latitude=" + MainActivity().latitude + "&longitude=" + MainActivity().longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=Europe%2FBerlin").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call : Call<DailyWeather> = service.fetchData()

        call.enqueue(object : Callback<DailyWeather> {

            override fun onResponse(response : Response<DailyWeather>?, retrofit : Retrofit?) {

                if(response!!.isSuccess){
                    val dailyWeather : DailyWeather = response.body() as DailyWeather
                    callback(dailyWeather)
                }
            }

            override fun onFailure(t : Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}