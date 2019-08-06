package kr.bsjo.weathersearch.scene

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kr.bsjo.weathersearch.api.ApiService
import kr.bsjo.weathersearch.api.gson.GsonHelper
import kr.bsjo.weathersearch.model.ModelLocationSearch
import kr.bsjo.weathersearch.model.ModelWeather
import kr.bsjo.weathersearch.util.networkThread

class WeatherVm {

    private val tag get() = this::class.java.canonicalName

    val disposable = CompositeDisposable()

    fun init() {
        locationSearch()
            .flatMapSingle { location ->
                ApiService.weather().location(location.woeid.toString())
                    .map {
                        listOf(
                            ModelWeather(
                                consolidated_weather = it.consolidated_weather.take(2),
                                title = location.title
                            )
                        )
                    }

            }
            .scan { t1: List<ModelWeather>, t2: List<ModelWeather> -> t1 + t2 }
            .networkThread()
            .subscribe({ result ->
                Log.d(tag, GsonHelper.toJson(result))
            }, { e ->
                Log.e(tag, e.toString())
            })
            .addTo(disposable)
    }

    private fun locationSearch(): Observable<ModelLocationSearch.Response> {
        return ApiService.weather()
            .locationSearch("se")
            .toObservable()
            .flatMapIterable { it }
    }
}