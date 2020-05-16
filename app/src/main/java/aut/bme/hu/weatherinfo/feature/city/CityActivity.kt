package aut.bme.hu.weatherinfo.feature.city

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import aut.bme.hu.weatherinfo.R

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

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
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
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCitySelected(city: String?) {
        TODO("Not yet implemented")
    }

}
