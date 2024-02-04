package com.example.atmosphere

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var places = PlacesImporter(this).parseJSON()

        displayPlaces(places)

        val placeInput = findViewById<EditText>(R.id.editPlace)
        val hint = "Recherche ville"
        placeInput.hint = hint

        placeInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s : Editable) {}

            override fun beforeTextChanged(s : CharSequence, start : Int,
                                           count : Int, after : Int) {
            }

            override fun onTextChanged(s : CharSequence, start : Int,
                                       before : Int, count : Int) {
                if (s.isEmpty()) {
                    placeInput.hint = hint
                } else {
                    placeInput.hint = ""
                }
                displayPlaces(places)
            }
        })
    }

    fun displayPlaces(places : Array<Place>) {
        val placesRecycler = findViewById<RecyclerView>(R.id.recycler_view)
        val placeInput = findViewById<EditText>(R.id.editPlace)

        if (placeInput.text.toString().length < 1) {
            placesRecycler.adapter = ItemAdapter(this, predefinedArrayOfPlaces())
            return
        }

        placesRecycler.adapter = ItemAdapter(this, filteredPlaces(placeInput.text.toString(), places))
    }

    fun filteredPlaces(name : String, places : Array<Place>) : Array<Place> {
        var filteredArrayOfPlaces : Array<Place> = emptyArray<Place>()

        for (place in places) {
            if (place.name.startsWith(name, true)) {
                filteredArrayOfPlaces = filteredArrayOfPlaces.plus(place)
            }
        }

        return predefinedArrayOfPlaces() + filteredArrayOfPlaces.sortedBy { it.name?.toString() }
    }

    fun predefinedArrayOfPlaces() : Array<Place> {
        return arrayOf(Place("Votre position", "", WeatherContext.initLocation.latitude, WeatherContext.initLocation.longitude))
    }

    fun closeSearch() {
        finish()
    }
}