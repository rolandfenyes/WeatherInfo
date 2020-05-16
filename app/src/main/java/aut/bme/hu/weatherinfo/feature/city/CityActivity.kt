package aut.bme.hu.weatherinfo.feature.city

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import aut.bme.hu.weatherinfo.R
import aut.bme.hu.weatherinfo.feature.details.DetailsActivity

import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.content_city.*

class CityActivity : AppCompatActivity(), CityAdapter.OnCitySelectedListener, AddCityDialogFragment.AddCityDialogListener {

    private lateinit var adapter: CityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setSupportActionBar(toolbar)

        initFab()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        MainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CityAdapter(this)
        adapter.addCity("Budapest")
        adapter.addCity("Debrecen")
        adapter.addCity("Sopron")
        adapter.addCity("Szeged")
        MainRecyclerView.adapter = adapter
    }

    private fun initFab() {
        fab.setOnClickListener {
            AddCityDialogFragment()
                .show(supportFragmentManager, AddCityDialogFragment::class.java.simpleName)
        }
    }

    override fun onCitySelected(city: String?) {
        val showDetailsIntent = Intent()
        showDetailsIntent.setClass(this, DetailsActivity::class.java)
        showDetailsIntent.putExtra(DetailsActivity.EXTRA_CITY_NAME, city!!)
        startActivity(showDetailsIntent)
    }

    override fun onCityAdded(city: String?) {
        adapter.addCity(city!!)
    }

}
