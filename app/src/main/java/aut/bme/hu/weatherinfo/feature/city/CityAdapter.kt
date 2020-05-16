package aut.bme.hu.weatherinfo.feature.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import aut.bme.hu.weatherinfo.R
import kotlinx.android.synthetic.main.item_city.view.*

class CityAdapter (private val listener: OnCitySelectedListener) : RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cities: MutableList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.CityViewHolder {
        return CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false))
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: CityAdapter.CityViewHolder, position: Int) {
        val item = cities[position]
        holder.bind(item)
    }

    fun addCity(newCity: String) {
        cities.add(newCity)
        notifyItemInserted(cities.size - 1)
    }

    fun removeCity(position: Int) {
        cities.removeAt(position)
        notifyItemRemoved(position)
        if (position < cities.size) {
            notifyItemRangeChanged(position, cities.size - position)
        }
    }

    inner class CityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var item: String? = null
        init {
            itemView.setOnClickListener {
                listener.onCitySelected(item)
            }
        }
        fun bind(newCity: String?) {
            item = newCity
            itemView.CityItemNameTextView.text = item
        }
    }

    interface OnCitySelectedListener {
        fun onCitySelected(city: String?)
    }


}