package aut.bme.hu.weatherinfo.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.fragment_details_main.*
import kotlinx.android.synthetic.main.fragment_details_main.tvHumidity
import kotlinx.android.synthetic.main.fragment_details_main.tvMaxTemp
import kotlinx.android.synthetic.main.fragment_details_main.tvMinTemp
import kotlinx.android.synthetic.main.fragment_details_main.tvPressure
import kotlinx.android.synthetic.main.fragment_details_main.tvTemperature
import kotlinx.android.synthetic.main.fragment_details_more.*

class DetailsMainFragment : Fragment() {

    private var weatherDataHolder: WeatherDataHolder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        weatherDataHolder = if (activity is WeatherDataHolder) {
            activity as WeatherDataHolder?
        } else {
            throw RuntimeException(
                "Activity must implement WeatherDataHolder interface!"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_details_main,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (weatherDataHolder!!.getWeatherData() != null) {
            displayWeatherData()
        }
    }

    private fun displayWeatherData() {
        val weather = weatherDataHolder!!.getWeatherData()?.weather!![0]
        val weatherData = weatherDataHolder!!.getWeatherData()
        tvMain!!.text = weather.main
        tvTemperature!!.text = "" + weatherData?.main!!.temp + "°C"
        tvMinTemp!!.text = "" + weatherData?.main!!.temp_min + "°C"
        tvMaxTemp!!.text = "" + weatherData?.main!!.temp_max + "°C"
        tvPressure!!.text = "" + weatherData?.main!!.pressure + "hPa"
        tvHumidity!!.text = "" + weatherData?.main!!.humidity + "%"
        Glide.with(this)
            .load("https://openweathermap.org/img/w/" + weather.icon + ".png")
            .transition(DrawableTransitionOptions().crossFade())
            .into(ivIcon)
    }
}