package kr.bsjo.weathersearch.model

object ModelLocation {

    data class Response(
        val consolidated_weather: List<ConsolidatedWeather>,
        val latt_long: String,
        val location_type: String,
        val parent: Parent,
        val sources: List<Source>,
        val sun_rise: String,
        val sun_set: String,
        val time: String,
        val timezone: String,
        val timezone_name: String,
        val title: String,
        val woeid: Int
    )

    data class ConsolidatedWeather(
        val air_pressure: Double = 0.0,
        val applicable_date: String = "",
        val created: String = "",
        val humidity: Int = 0,
        val id: Long = 0,
        val max_temp: Double = 0.0,
        val min_temp: Double = 0.0,
        val predictability: Int = 0,
        val the_temp: Double = 0.0,
        val visibility: Double = 0.0,
        val weather_state_abbr: String = "",
        val weather_state_name: String = "",
        val wind_direction: Float = 0.0f,
        val wind_direction_compass: String = "",
        val wind_speed: Double = 0.0
    )

    data class Source(
        val crawl_rate: Int,
        val slug: String,
        val title: String,
        val url: String
    )

    data class Parent(
        val latt_long: String,
        val location_type: String,
        val title: String,
        val woeid: Int
    )
}

