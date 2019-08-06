package kr.bsjo.weathersearch.api

import kr.bsjo.weathersearch.api.service.ApiWeather

object ApiService {
    const val baseUrl = "https://www.metaweather.com"

    fun weather() = RetrofitAdapter.getInstance(baseUrl)
        .create(ApiWeather::class.java)
}