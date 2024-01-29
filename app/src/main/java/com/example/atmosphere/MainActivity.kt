package com.example.atmosphere

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent.Callback
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient : FusedLocationProviderClient

    private var hourlies : Array<HourlyItem> = emptyArray<HourlyItem>()
    private var dailies : Array<DailyItem> = emptyArray<DailyItem>()

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var menuIcon : ImageView

    private lateinit var refreshIcon : LinearLayout
    private lateinit var searchIcon : LinearLayout

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherContext.updateLocationCallback = { updateLocation() }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        refreshIcon = findViewById<LinearLayout>(R.id.refresh)
        refreshIcon.setOnClickListener {
            refresh()
            closeDrawer(drawerLayout)
        }

        searchIcon = findViewById<LinearLayout>(R.id.search)
        searchIcon.setOnClickListener {
            openSearchActivity()
            closeDrawer(drawerLayout)
        }

        drawerLayout = findViewById(R.id.drawerLayout)
        menuIcon = findViewById<ImageView>(R.id.menu)

        menuIcon.setOnClickListener {
            openDrawer(drawerLayout)
        }
    }

    fun displayResponse(response : String) {
        val text = findViewById<TextView>(R.id.location)
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
                    WeatherContext.initLocation = location
                    WeatherContext.updateLocation(Place("Votre position", "", location.latitude, location.longitude))
                } else {
                    // display error
                }
            }
        }
    }

    fun refresh() {
        getHourlyForecast()
        getDailyForecast()
    }

    fun getHourlyForecast() {
        ApiCall().fetchHourly(this) { hourlyWeather ->
            this.hourlies = emptyArray()
            for(i in 0..23) {
                this.hourlies = this.hourlies.plus(
                    HourlyItem(hourlyWeather.hourly.time[i],
                        hourlyWeather.hourly.temperature_2m[i],
                        hourlyWeather.hourly.precipitation[i],
                        hourlyWeather.hourly.weather_code[i]
                    )
                )
            }
            val hourlyItemsRecycler = findViewById<RecyclerView>(R.id.hourlyRecycler)
            hourlyItemsRecycler.adapter = HourlyItemAdapter(this.hourlies)
        }
    }

    fun getDailyForecast() {
        ApiCall().fetchDaily(this) { dailyWeather ->
            this.dailies = emptyArray()
            for(i in 0..6) {
                this.dailies = this.dailies.plus(
                    DailyItem(dailyWeather.daily.time[i],
                        dailyWeather.daily.weather_code[i],
                        dailyWeather.daily.temperature_2m_max[i],
                        dailyWeather.daily.temperature_2m_min[i],
                        dailyWeather.daily.precipitation_probability_max[i]
                    )
                )
            }
            val dailyItemsRecycler = findViewById<RecyclerView>(R.id.dailyRecycler)
            dailyItemsRecycler.adapter = DailyItemAdapter(this.dailies)
        }
    }

    fun openDrawer(drawerLayout : DrawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    fun closeDrawer(drawerLayout : DrawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun openSearchActivity() {
        var intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    fun updateLocation() {
        val text = findViewById<TextView>(R.id.location)
        text.text = WeatherContext.location.name
        refresh()
    }
}