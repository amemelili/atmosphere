package com.example.atmosphere

import android.content.Context
import android.provider.ContactsContract.Data
import android.widget.Toast

import retrofit.*

class ApiCall {
    fun fetchData(context: Context, callback: (UserData) -> Unit) {

        val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://reqres.in/").addConverterFactory(
            GsonConverterFactory.create()).build()

        val service: ApiService = retrofit.create<ApiService>(ApiService::class.java)

        val call: Call<UserData> = service.fetchData()

        call.enqueue(object : Callback<UserData> {

            override fun onResponse(response: Response<UserData>?, retrofit: Retrofit?) {

                if(response!!.isSuccess){
                    val userData: UserData = response.body() as UserData
                    callback(userData)
                }
            }

            override fun onFailure(t: Throwable?) {
                Toast.makeText(context, "Request Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}