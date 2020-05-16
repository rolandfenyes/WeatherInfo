package aut.bme.hu.weatherinfo.feature.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import aut.bme.hu.weatherinfo.R
import aut.bme.hu.weatherinfo.model.WeatherData
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
                0 -> getString(R.string.main)
                1 -> getString(R.string.details)
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

    private fun displayWeatherData(receivedWeatherData: WeatherData?) {

        weatherData = receivedWeatherData

        val detailsPagerAdapter = DetailsPagerAdapter(this)
        mainViewPager.adapter = detailsPagerAdapter
    }
}
