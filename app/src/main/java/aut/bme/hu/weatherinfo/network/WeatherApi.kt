package aut.bme.hu.weatherinfo.network

import aut.bme.hu.weatherinfo.model.current.WeatherData
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForecast
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface WeatherApi {

    @GET("/data/2.5/weather")
    fun getWeather(
        @Query("q") cityName: String?,
        @Query("units") units: String?,
        @Query("appid") appId: String?
    ): Call<WeatherData?>?

    @GET("/data/2.5/forecast/daily")
    fun get7DaysForecast(
        @Query("q") cityName: String?,
        @Query("cnt") cnt: Int?,
        @Query("appid") appId: String?
    ): Call<WeatherDataForecast?>?
}
