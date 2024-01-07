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
    public var latitude : Double = 0.0
    public var longitude : Double = 0.0

    private lateinit var dailyWeather : DailyWeather

    private lateinit var drawerLayout : DrawerLayout
    private lateinit var menuIcon : ImageView

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherContext.updateLocationCallback = { updateLocation() }

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLocation()

        val searchIcon = findViewById<LinearLayout>(R.id.search)
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
                    setInitLocation(location.latitude, location.longitude)
                    getDailyForecast()
                } else {
                    // display error
                }
            }
        }
    }

    fun setInitLocation(latitude : Double, longitude : Double) {
        this.latitude = latitude
        this.longitude = longitude
    }

    fun getDailyForecast() {
        ApiCall().fetchData(this) { dailyWeather ->
            displayResponse(WeatherStatus.getStatusByWeatherCode(dailyWeather.daily.weather_code.get(0)))
            this.dailyWeather = dailyWeather
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
    }
}