package aut.bme.hu.weatherinfo.model.current

import aut.bme.hu.weatherinfo.model.current.MainWeatherData
import aut.bme.hu.weatherinfo.model.current.Weather
import aut.bme.hu.weatherinfo.model.current.Wind

class WeatherData {
    var coord: Coordinates? = null
    var weather: List<Weather>? = null
    var main: MainWeatherData? = null
    var wind: Wind? = null
}