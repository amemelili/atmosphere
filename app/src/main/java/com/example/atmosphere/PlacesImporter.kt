package com.example.atmosphere

import android.content.Context
import com.google.gson.Gson
import java.io.InputStream

class PlacesImporter(var context: Context) {

    fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val  inputStream: InputStream = context.assets.open("places.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun parseJSON(): Places {
        return Gson().fromJson<Places>(readJSONFromAsset(), Places::class.java)
    }

}