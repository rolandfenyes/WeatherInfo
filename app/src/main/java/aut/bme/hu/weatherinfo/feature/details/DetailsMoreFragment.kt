package aut.bme.hu.weatherinfo.feature.details

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import aut.bme.hu.weatherinfo.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (weatherDataHolder!!.getWeatherForecastData() != null) {
            showWeatherData()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showWeatherData() {
        val weather = weatherDataHolder!!.getWeatherForecastData()
        tvDate!!.text = getDate(0)
        tvMax!!.text = "" + weather!!.daily!![0].temp!!.day + "°C"
        tvMin!!.text = "" + weather!!.daily!![0].temp!!.min + "°C"
        setImageForIV(ivDayIcon ,weather!!.daily!![0].weather!![0].icon!!)

        tvDate2!!.text = getDate(1)
        tvMax2!!.text = "" + weather!!.daily!![1].temp!!.day + "°C"
        tvMin2!!.text = "" + weather!!.daily!![1].temp!!.min + "°C"
        setImageForIV(ivDayIcon2 ,weather!!.daily!![1].weather!![0].icon!!)

        tvDate3!!.text = getDate(2)
        tvMax3!!.text = "" + weather!!.daily!![2].temp!!.day + "°C"
        tvMin3!!.text = "" + weather!!.daily!![2].temp!!.min + "°C"
        setImageForIV(ivDayIcon3 ,weather!!.daily!![2].weather!![0].icon!!)

        tvDate4!!.text = getDate(3)
        tvMax4!!.text = "" + weather!!.daily!![3].temp!!.day + "°C"
        tvMin4!!.text = "" + weather!!.daily!![3].temp!!.min + "°C"
        setImageForIV(ivDayIcon4 ,weather!!.daily!![3].weather!![0].icon!!)

        tvDate5!!.text = getDate(4)
        tvMax5!!.text = "" + weather!!.daily!![4].temp!!.day + "°C"
        tvMin5!!.text = "" + weather!!.daily!![4].temp!!.min + "°C"
        setImageForIV(ivDayIcon5 ,weather!!.daily!![4].weather!![0].icon!!)

        tvDate6!!.text = getDate(5)
        tvMax6!!.text = "" + weather!!.daily!![5].temp!!.day + "°C"
        tvMin6!!.text = "" + weather!!.daily!![5].temp!!.min + "°C"
        setImageForIV(ivDayIcon6 ,weather!!.daily!![5].weather!![0].icon!!)

        tvDate7!!.text = getDate(6)
        tvMax7!!.text = "" + weather!!.daily!![6].temp!!.day + "°C"
        tvMin7!!.text = "" + weather!!.daily!![6].temp!!.min + "°C"
        setImageForIV(ivDayIcon7 ,weather!!.daily!![6].weather!![0].icon!!)
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
            dayOfWeek = 1
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