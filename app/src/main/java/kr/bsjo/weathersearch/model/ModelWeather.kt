package kr.bsjo.weathersearch.model

data class ModelWeather(
    val weathers: List<ModelLocation.ConsolidatedWeather> = mutableListOf(),
    val title: String = ""
) {
    fun toList() = listOf(this)
}