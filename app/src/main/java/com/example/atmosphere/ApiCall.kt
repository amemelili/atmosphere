package com.example.atmosphere

import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast

import retrofit.*

class ApiCall {

    fun fetchRealtime(context : Context, callback : (RealtimeWeather) -> Unit) {

        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/forecast?latitude=" + WeatherContext.location.latitude + "&longitude=" + WeatherContext.location.longitude + "&current=temperature_2m,precipitation,weather_code&timezone=Europe%2FBerlin&forecast_days=1").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call : Call<RealtimeWeather> = service.fetchRealtime()

        call.enqueue(object : Callback<RealtimeWeather> {

            override fun onResponse(response : Response<RealtimeWeather>?, retrofit : Retrofit?) {

                if(response!!.isSuccess){
                    val realtimeWeather : RealtimeWeather = response.body() as RealtimeWeather
                    callback(realtimeWeather)
                }
            }

            override fun onFailure(t : Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun fetchHourly(context : Context, callback : (HourlyWeather) -> Unit) {

        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/forecast?latitude=" + WeatherContext.location.latitude + "&longitude=" + WeatherContext.location.longitude + "&hourly=temperature_2m,precipitation,weather_code&timezone=Europe%2FBerlin&forecast_days=1").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call : Call<HourlyWeather> = service.fetchHourly()

        call.enqueue(object : Callback<HourlyWeather> {

            override fun onResponse(response : Response<HourlyWeather>?, retrofit : Retrofit?) {

                if(response!!.isSuccess){
                    val hourlyWeather : HourlyWeather = response.body() as HourlyWeather
                    callback(hourlyWeather)
                }
            }

            override fun onFailure(t : Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun fetchDaily(context : Context, callback : (DailyWeather) -> Unit) {

        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.open-meteo.com/v1/forecast?latitude=" + WeatherContext.location.latitude + "&longitude=" + WeatherContext.location.longitude + "&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=Europe%2FBerlin").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service : ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call : Call<DailyWeather> = service.fetchDaily()

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