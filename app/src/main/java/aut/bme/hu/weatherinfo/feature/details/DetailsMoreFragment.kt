package aut.bme.hu.weatherinfo.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import kotlinx.android.synthetic.main.item_day.*

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
        if (weatherDataHolder!!.getWeatherForecastData() != null) {
            showWeatherData()
        }
    }

    private fun showWeatherData() {
        val weather = weatherDataHolder!!.getWeatherForecastData()
        tvMax!!.text = "" + weather!!.daily!![0].temp!!.day
    }
}