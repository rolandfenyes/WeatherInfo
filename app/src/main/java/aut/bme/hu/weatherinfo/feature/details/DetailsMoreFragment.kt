package aut.bme.hu.weatherinfo.feature.details

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForecast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_details_main.*
import kotlinx.android.synthetic.main.fragment_details_more.*
import java.time.LocalDate
import java.util.*

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
            loadChart()
        }
    }

    private fun loadChart() {
        val weather = weatherDataHolder!!.getWeatherForecastData()
        var entriesMax: ArrayList<Entry> = ArrayList()
        entriesMax.add(Entry(0F, weather!!.daily!![0].temp!!.day))
        entriesMax.add(Entry(1F, weather!!.daily!![1].temp!!.day))
        entriesMax.add(Entry(2F, weather!!.daily!![2].temp!!.day))
        entriesMax.add(Entry(3F, weather!!.daily!![3].temp!!.day))
        entriesMax.add(Entry(4F, weather!!.daily!![4].temp!!.day))
        entriesMax.add(Entry(5F, weather!!.daily!![5].temp!!.day))
        entriesMax.add(Entry(6F, weather!!.daily!![6].temp!!.day))

        var dataSet: LineDataSet = LineDataSet(entriesMax, "Max")
        dataSet.setColor(Color.BLUE)
        dataSet.label = "Max"

        var lineData: LineData = LineData(dataSet)
        lcSevenDaysTemperatureChanging.data = lineData
        lcSevenDaysTemperatureChanging.invalidate()
    }

    private fun showWeatherData() {
        val weather = weatherDataHolder!!.getWeatherForecastData()

        setLineData(tvDate, tvMax, tvMin, ivDayIcon, 0, weather!!)
        setLineData(tvDate2, tvMax2, tvMin2, ivDayIcon2, 1, weather!!)
        setLineData(tvDate3, tvMax3, tvMin3, ivDayIcon3, 2, weather!!)
        setLineData(tvDate4, tvMax4, tvMin4, ivDayIcon4, 3, weather!!)
        setLineData(tvDate5, tvMax5, tvMin5, ivDayIcon5, 4, weather!!)
        setLineData(tvDate6, tvMax6, tvMin6, ivDayIcon6, 5, weather!!)
        setLineData(tvDate7, tvMax7, tvMin7, ivDayIcon7, 6, weather!!)
    }

    private fun setLineData(dateText: TextView, maxText: TextView, minText: TextView, image: ImageView, index: Int, weather: WeatherDataForecast) {
        dateText!!.text = getDate(index)
        maxText!!.text = "" + weather!!.daily!![index].temp!!.day + "°C"
        minText!!.text = "" + weather!!.daily!![index].temp!!.min + "°C"
        setImageForIV(image ,weather!!.daily!![index].weather!![0].icon!!)
    }

    private fun setImageForIV(imageView: ImageView, icon: String) {
        Glide.with(this)
            .load("https://openweathermap.org/img/w/" + icon + ".png")
            .transition(DrawableTransitionOptions().crossFade())
            .into(imageView)
    }

    private fun getDate(plusDay: Int): String {
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_WEEK)
        var dayOfWeek = today + plusDay
        if (dayOfWeek >= 8) {
            dayOfWeek = dayOfWeek - 7
        }
        return when (dayOfWeek) {
            Calendar.MONDAY -> "Mon"
            Calendar.TUESDAY -> "Tue"
            Calendar.WEDNESDAY -> "Wen"
            Calendar.THURSDAY -> "Thu"
            Calendar.FRIDAY -> "Fri"
            Calendar.SATURDAY -> "Sat"
            Calendar.SUNDAY -> "Sun"
            else -> today.toString()
        }

    }
}