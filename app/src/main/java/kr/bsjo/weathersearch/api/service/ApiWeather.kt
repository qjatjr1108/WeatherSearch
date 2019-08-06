package kr.bsjo.weathersearch.api.service

import io.reactivex.Observable
import io.reactivex.Single
import kr.bsjo.weathersearch.model.ModelLocation
import kr.bsjo.weathersearch.model.ModelLocationSearch
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiWeather {

    @GET("search")
    fun locationSearch(@Query("query") word: String): Single<List<ModelLocationSearch.Response>>

    @GET("{woeid}")
    fun location(@Path("woeid") woeid: String): Single<ModelLocation.Response>
}