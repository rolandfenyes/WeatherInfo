package aut.bme.hu.weatherinfo.feature.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import aut.bme.hu.weatherinfo.R
import aut.bme.hu.weatherinfo.model.current.WeatherData
import aut.bme.hu.weatherinfo.model.hourly.WeatherDataHourly
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForecast
import aut.bme.hu.weatherinfo.network.NetworkManager
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsActivity : AppCompatActivity(), WeatherDataHolder {

    companion object {
        private const val TAG = "DetailsActivity"
        const val EXTRA_CITY_NAME = "extra.city_name"
    }

    private var city: String? = null
    private var weatherData: WeatherData? = null
    private var weatherDataForecast: WeatherDataForecast? = null
    private var hourlyWeatherData: WeatherDataHourly? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        city = intent.getStringExtra(EXTRA_CITY_NAME)

        supportActionBar!!.title = getString(R.string.weather, city)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()

        val detailsPageAdapter = DetailsPagerAdapter(this)
        mainViewPager.adapter = detailsPageAdapter

        TabLayoutMediator(tab_layout, mainViewPager) { tab, position ->
            tab.text = when(position) {
                0 -> getString(R.string.today)
                1 -> getString(R.string.next_week)
                else -> ""
            }
        }.attach()
        loadWeatherData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getWeatherData(): WeatherData? {
        return weatherData
    }

    override fun getWeatherForecastData(): WeatherDataForecast? {
        return weatherDataForecast
    }

    override fun getWeatherHourlyData(): WeatherDataHourly? {
        return hourlyWeatherData
    }

    private fun loadWeatherData(){
        NetworkManager.getWeather(city)!!.enqueue(object : Callback<WeatherData?> {

            override fun onResponse(
                call: Call<WeatherData?>,
                response: Response<WeatherData?>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    displayWeatherData(response.body())
                } else {
                    Toast.makeText(
                        this@DetailsActivity,
                        "Error: " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(
                call: Call<WeatherData?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(
                    this@DetailsActivity,
                    "Network request error occurred, check LOG",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun loadWeeklyWeatherData() {
        NetworkManager.get7DaysForecast(weatherData!!.coord!!.lat, weatherData!!.coord!!.lon)!!.enqueue(object : Callback<WeatherDataForecast?> {

            override fun onResponse(
                call: Call<WeatherDataForecast?>,
                response: Response<WeatherDataForecast?>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    displayWeatherDataForecast(response.body())
                } else {
                    Toast.makeText(
                        this@DetailsActivity,
                        "Error: " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(
                call: Call<WeatherDataForecast?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(
                    this@DetailsActivity,
                    "Network request error occurred, check LOG",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun loadHourlyWeatherData() {
        NetworkManager.getHourlyForecast(weatherData!!.coord!!.lat, weatherData!!.coord!!.lon)!!.enqueue(object : Callback<WeatherDataHourly?> {

            override fun onResponse(
                call: Call<WeatherDataHourly?>,
                response: Response<WeatherDataHourly?>
            ) {
                Log.d(TAG, "onResponse: " + response.code())
                if (response.isSuccessful) {
                    displayHourlyWeatherData(response.body())
                } else {
                    Toast.makeText(
                        this@DetailsActivity,
                        "Error: " + response.message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(
                call: Call<WeatherDataHourly?>,
                throwable: Throwable
            ) {
                throwable.printStackTrace()
                Toast.makeText(
                    this@DetailsActivity,
                    "Network request error occurred, check LOG",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun displayWeatherData(receivedWeatherData: WeatherData?) {

        weatherData = receivedWeatherData

        loadHourlyWeatherData()
        loadWeeklyWeatherData()

        val detailsPagerAdapter = DetailsPagerAdapter(this)
        mainViewPager.adapter = detailsPagerAdapter
    }

    private fun displayWeatherDataForecast(receivedWeatherData: WeatherDataForecast?) {
        weatherDataForecast = receivedWeatherData
    }
    private fun displayHourlyWeatherData(receivedWeatherData: WeatherDataHourly?) {
        hourlyWeatherData = receivedWeatherData
    }
}
