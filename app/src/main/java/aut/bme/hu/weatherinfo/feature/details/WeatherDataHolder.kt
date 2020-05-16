package aut.bme.hu.weatherinfo.feature.details

import aut.bme.hu.weatherinfo.model.WeatherData

interface WeatherDataHolder {
    fun getWeatherData(): WeatherData?
}