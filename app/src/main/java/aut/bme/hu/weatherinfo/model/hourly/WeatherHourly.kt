package aut.bme.hu.weatherinfo.model.hourly

import aut.bme.hu.weatherinfo.model.current.Weather

class WeatherHourly {
    var temp: Float = 0F
    var weather: List<Weather>? = null
}