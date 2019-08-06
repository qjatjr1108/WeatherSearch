package kr.bsjo.weathersearch.model

object ModelLocationSearch {
    data class Response(
        val latt_long: String,
        val location_type: String,
        val title: String,
        val woeid: Int
    )
}