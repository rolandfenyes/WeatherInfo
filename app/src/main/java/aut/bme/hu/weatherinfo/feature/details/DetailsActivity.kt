package aut.bme.hu.weatherinfo.feature.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import aut.bme.hu.weatherinfo.R
import aut.bme.hu.weatherinfo.model.WeatherData
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_details.*

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
        }.attach()    }

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
}
