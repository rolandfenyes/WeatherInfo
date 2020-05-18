package aut.bme.hu.weatherinfo.network

import aut.bme.hu.weatherinfo.model.current.WeatherData
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForecast
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val weatherApi: WeatherApi

    private const val SERVICE_URL = "https://api.openweathermap.org"
    private const val APP_ID = "61b3598d0004ba06c76d3e79d6f6c36f"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    fun getWeather(city: String?): Call<WeatherData?>? {
        return weatherApi.getWeather(city, "metric", APP_ID)
    }

    fun get7DaysForecast(lat: Float?, lon: Float): Call<WeatherDataForecast?>? {
        return weatherApi.get7DaysForecast(lat,lon, "hourly", APP_ID, "metric")
    }
}