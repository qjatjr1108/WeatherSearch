package kr.bsjo.weathersearch.scene

import kr.bsjo.weathersearch.api.ApiService
import kr.bsjo.weathersearch.model.ModelLocation
import kr.bsjo.weathersearch.model.ModelWeather

class WeatherItemVm(val model: ModelWeather, val isHeader: Boolean = false) {

    val city get() = model.title

    private val today = if (isHeader) ModelLocation.ConsolidatedWeather() else model.weathers[0]
    private val tomorrow = if (isHeader) ModelLocation.ConsolidatedWeather() else model.weathers[1]

    val todayImageUrl get() = today.weather_state_abbr.getImageUrl()
    val tomorrowImageUrl get() = tomorrow.weather_state_abbr.getImageUrl()

    val todayWeatherStateName get() = today.weather_state_name
    val tomorrowWeatherStateName get() = tomorrow.weather_state_name

    val todayTempWithHumidity get() = "${today.max_temp.toInt()} ${today.humidity}"
    val tomorrowTempWithHumidity get() = "${tomorrow.max_temp.toInt()} ${tomorrow.humidity}"

    private fun String.getImageUrl() = "${ApiService.baseUrl}/static/img/weather/png/64/$this.png"

    companion object {
        fun createHeader() = WeatherItemVm(ModelWeather(), isHeader = true)
    }
}