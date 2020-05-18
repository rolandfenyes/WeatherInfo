package aut.bme.hu.weatherinfo.network

import aut.bme.hu.weatherinfo.model.current.WeatherData
import aut.bme.hu.weatherinfo.model.hourly.WeatherDataHourly
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

    @GET("/data/2.5/onecall")
    fun get7DaysForecast(
        @Query("lat") latitude: Float?,
        @Query("lon") longtitude: Float?,
        @Query("exclude") exclude: String?,
        @Query("appid") appId: String?,
        @Query("units") units: String?
    ): Call<WeatherDataForecast?>?

    @GET("/data/2.5/onecall")
    fun getHourlyForecast(
        @Query("lat") latitude: Float?,
        @Query("lon") longtitude: Float?,
        @Query("exclude") exclude: String?,
        @Query("appid") appId: String?,
        @Query("units") units: String?
    ): Call<WeatherDataHourly?>?
}
