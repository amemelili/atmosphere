package com.example.atmosphere

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DailyItemAdapter(
    private val dailyItems : Array<DailyItem>
) : RecyclerView.Adapter<DailyItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val maxTemp: TextView = view.findViewById(R.id.temp)
        val minTemp: TextView = view.findViewById(R.id.temp_min)
        val precipitation: TextView = view.findViewById(R.id.precipitation)
        val date: TextView = view.findViewById(R.id.time)
        val weatherStatus : ImageView = view.findViewById(R.id.weatherStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.daily_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val dailyItem = dailyItems.get(position)
        holder.maxTemp.text = dailyItem.temperature_2m_max.toString() + "°"
        holder.minTemp.text = dailyItem.temperature_2m_min.toString() + "°"
        holder.precipitation.text = dailyItem.precipitation_probability_max.toString() + "%"
        holder.weatherStatus.setImageResource(WeatherStatus.getImageStatusByWeatherCode(dailyItem.weather_code))

        var day = dailyItem.time[8].toString() + dailyItem.time[9].toString()
        var month = dailyItem.time[5].toString() + dailyItem.time[6].toString()
        var year = dailyItem.time[0].toString() + dailyItem.time[1].toString() + dailyItem.time[2].toString() + dailyItem.time[3].toString()

        holder.date.text = day + "/" + month + "/" + year
    }

    override fun getItemCount() = dailyItems.size
}