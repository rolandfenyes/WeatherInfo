package aut.bme.hu.weatherinfo.network

import aut.bme.hu.weatherinfo.model.WeatherData
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

}