package com.example.atmosphere

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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

        val placeInput = findViewById<EditText>(R.id.editPlace)
        placeInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                displayPlaces(places)
            }
        })

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        getLastKnownLocation()
    }

    fun displayResponse(response : String) {
        val text = findViewById<TextView>(R.id.textView)
        text.text = response
    }

    fun displayPlaces(places : Array<Place>) {
        val placesRecycler = findViewById<RecyclerView>(R.id.recycler_view)
        val placeInput = findViewById<EditText>(R.id.editPlace)

        if (placeInput.text.toString().length < 1) {
            placesRecycler.adapter = ItemAdapter(this, emptyArray())
            return
        }

        placesRecycler.adapter = ItemAdapter(this, filteredPlaces(placeInput.text.toString(), places))
    }

    fun filteredPlaces(name : String, places : Array<Place>) : Array<Place> {
        var filteredArrayOfPlaces : Array<Place> = emptyArray<Place>()

        for (place in places) {
            if (place.name.contains(name, true)) {
                filteredArrayOfPlaces = filteredArrayOfPlaces.plus(place)
            }
        }

        return filteredArrayOfPlaces
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
                    displayResponse(location.latitude.toString() + " " + location.longitude.toString())
                } else {
                    displayResponse("erreur dans le chargement de la position")
                }
            }
        }
    }
}