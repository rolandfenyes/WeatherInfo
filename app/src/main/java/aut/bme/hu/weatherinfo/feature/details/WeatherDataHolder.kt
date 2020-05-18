package aut.bme.hu.weatherinfo.feature.details

import aut.bme.hu.weatherinfo.model.current.WeatherData

interface WeatherDataHolder {
    fun getWeatherData(): WeatherData?
}