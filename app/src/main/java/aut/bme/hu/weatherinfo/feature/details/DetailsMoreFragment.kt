package aut.bme.hu.weatherinfo.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import kotlinx.android.synthetic.main.fragment_details_more.*

class DetailsMoreFragment : Fragment() {

    private var weatherDataHolder: WeatherDataHolder? = null

    override fun onCreate( savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherDataHolder = if (activity is WeatherDataHolder) {
            activity as WeatherDataHolder?
        } else {
            throw RuntimeException("Activity must implement WeatherDataHolder interface!")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_details_more,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (weatherDataHolder!!.getWeatherData() != null) {
            showWeatherData()
        }
    }

    private fun showWeatherData() {
        val weatherData = weatherDataHolder!!.getWeatherData()
        tvTemperature!!.text = "" + weatherData?.main!!.temp
        tvMinTemp!!.text = "" + weatherData?.main!!.temp_min
        tvMaxTemp!!.text = "" + weatherData?.main!!.temp_max
        tvPressure!!.text = "" + weatherData?.main!!.pressure
        tvHumidity!!.text = "" + weatherData?.main!!.humidity
    }
}