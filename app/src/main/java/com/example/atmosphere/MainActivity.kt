package com.example.atmosphere

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*ApiCall().fetchData(this) { userData ->
            displayResponse(userData.data.first_name)
        }*/

        var places = PlacesImporter(this).parseJSON()
        displayPlaces(places)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastKnownLocation()
    }

    fun displayResponse(response : String) {
        val text = findViewById<TextView>(R.id.textView)
        text.text = response
    }

    fun displayPlaces(places : Array<Place>) {
        val placesRecycler = findViewById<RecyclerView>(R.id.recycler_view)
        placesRecycler.adapter = ItemAdapter(this, places)
    }

    fun getLastKnownLocation() {
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
                    displayResponse(location.latitude.toString())
                } else {
                    displayResponse("fdp")
                }
            }
        }
    }
}