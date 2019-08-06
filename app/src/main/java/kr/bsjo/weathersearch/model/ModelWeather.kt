package kr.bsjo.weathersearch.model

data class ModelWeather(
    val consolidated_weather: List<ModelLocation.ConsolidatedWeather>,
    val title: String
)