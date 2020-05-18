package aut.bme.hu.weatherinfo.feature.details

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_details_main.*
import kotlinx.android.synthetic.main.fragment_details_main.tvHumidity
import kotlinx.android.synthetic.main.fragment_details_main.tvMaxTemp
import kotlinx.android.synthetic.main.fragment_details_main.tvMinTemp
import kotlinx.android.synthetic.main.fragment_details_main.tvPressure
import kotlinx.android.synthetic.main.fragment_details_main.tvTemperature

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
        if (weatherDataHolder!!.getWeatherHourlyData() != null) {
            loadChart()
        }
    }

    private fun loadChart() {
        val weather = weatherDataHolder!!.getWeatherHourlyData()
        var entries: ArrayList<Entry> = ArrayList()

        entries.add(Entry(0F, weather!!.hourly!![0].temp))
        entries.add(Entry(1F, weather!!.hourly!![1].temp))
        entries.add(Entry(2F, weather!!.hourly!![2].temp))
        entries.add(Entry(3F, weather!!.hourly!![3].temp))
        entries.add(Entry(4F, weather!!.hourly!![4].temp))
        entries.add(Entry(5F, weather!!.hourly!![5].temp))
        entries.add(Entry(6F, weather!!.hourly!![6].temp))

        var dataSet: LineDataSet = LineDataSet(entries, "Hours")
        dataSet.setColor(Color.BLUE)

        var lineData: LineData = LineData(dataSet)
        lcHourlyWeather.data = lineData
        lcHourlyWeather.invalidate()
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