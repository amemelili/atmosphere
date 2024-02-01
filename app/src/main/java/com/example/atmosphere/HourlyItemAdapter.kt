package com.example.atmosphere

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HourlyItemAdapter(
    private val hourlyItems : Array<HourlyItem>
) : RecyclerView.Adapter<HourlyItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val temp: TextView = view.findViewById(R.id.temp)
        val precipitation: TextView = view.findViewById(R.id.precipitation)
        val time: TextView = view.findViewById(R.id.time)
        val weatherStatus : ImageView = view.findViewById(R.id.weatherStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.hourly_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val hourlyItem = hourlyItems.get(position)
        holder.temp.text = hourlyItem.temperature_2m.toString() + "°"
        holder.precipitation.text = hourlyItem.precipitation.toString() + "mm"
        holder.weatherStatus.setImageResource(WeatherStatus.getImageStatusByWeatherCode(hourlyItem.weather_code))

        var time = hourlyItem.time[11].toString() +
                hourlyItem.time[12].toString() +
                hourlyItem.time[13].toString() +
                hourlyItem.time[14].toString() +
                hourlyItem.time[15].toString()

                holder.time.text = time
    }

    override fun getItemCount() = hourlyItems.size
}