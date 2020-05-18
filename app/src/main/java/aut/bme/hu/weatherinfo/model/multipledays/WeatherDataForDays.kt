package aut.bme.hu.weatherinfo.model.multipledays

import aut.bme.hu.weatherinfo.model.current.Weather

class WeatherDataForDays {
    var weather: List<Weather>? = null
    var temp: TempOfDay? = null
}