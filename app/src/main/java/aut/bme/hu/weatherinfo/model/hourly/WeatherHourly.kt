package aut.bme.hu.weatherinfo.model.hourly

import aut.bme.hu.weatherinfo.model.current.Weather

class WeatherHourly {
    var dt: Int = 0
    var temp: Float = 0f
    var feels_like: Float = 0f
    var pressure: Int = 0
    var humidity: Int = 0
    var dew_point: Float = 0f
    var clouds: Int = 0
    var wind_speed: Float = 0f
    var wind_deg: Int = 0
    var weather: List<Weather>? = null
}