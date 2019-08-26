package com.edsusantoo.bismillah.myweather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_weather.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val mData = ArrayList<WeatherItems>()

    fun setData(items: ArrayList<WeatherItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mData[position])
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvNamaKota: TextView = view.textKota
        private val tvTemperature: TextView = view.textTemp
        private val tvDescription: TextView = view.textDesc

        fun bind(weatherItems: WeatherItems) {
            tvNamaKota.text = weatherItems.name
            tvTemperature.text = weatherItems.temperature
            tvDescription.text = weatherItems.description
        }
    }

}