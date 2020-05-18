package aut.bme.hu.weatherinfo.feature.details

import aut.bme.hu.weatherinfo.model.current.WeatherData
import aut.bme.hu.weatherinfo.model.hourly.WeatherDataHourly
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForDays
import aut.bme.hu.weatherinfo.model.multipledays.WeatherDataForecast

interface WeatherDataHolder {
    fun getWeatherData(): WeatherData?
    fun getWeatherForecastData(): WeatherDataForecast?
    fun getWeatherHourlyData(): WeatherDataHourly?
}