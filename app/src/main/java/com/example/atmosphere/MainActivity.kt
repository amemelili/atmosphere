package com.example.atmosphere

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    public var latitude : Double = 0.0
    public var longitude : Double = 0.0

    private lateinit var dailyWeather : DailyWeather

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        val searchIcon = findViewById<ImageView>(R.id.searchIcon)
        searchIcon.setOnClickListener {
            var intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
    }

    fun displayResponse(response : String) {
        val text = findViewById<TextView>(R.id.textView)
        text.text = response
    }

    fun getLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        } else {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location : Location? ->
                if (location != null) {
                    setLocation(location.latitude, location.longitude)
                    getDailyForecast()
                } else {
                    // display error
                }
            }
        }
    }

    fun setLocation(latitude : Double, longitude : Double) {
        this.latitude = latitude
        this.longitude = longitude
    }

    fun getDailyForecast() {
        ApiCall().fetchData(this) { dailyWeather ->
            displayResponse(dailyWeather.daily.time.get(0).toString())
            this.dailyWeather = dailyWeather
        }
    }
}