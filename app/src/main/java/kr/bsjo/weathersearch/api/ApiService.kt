package kr.bsjo.weathersearch.api

import kr.bsjo.weathersearch.api.service.ApiWeather

object ApiService {
    private const val baseUrl = "https://www.metaweather.com/api/location/"

    fun weather() = RetrofitAdapter.getInstance(baseUrl)
        .create(ApiWeather::class.java)
}