package kr.bsjo.weathersearch.scene

import android.util.Log
import androidx.databinding.ObservableBoolean
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kr.bsjo.weathersearch.BR
import kr.bsjo.weathersearch.R
import kr.bsjo.weathersearch.api.ApiService
import kr.bsjo.weathersearch.api.gson.GsonHelper
import kr.bsjo.weathersearch.databinding.BaseRecyclerViewAdapter
import kr.bsjo.weathersearch.databinding.ItemWeatherBinding
import kr.bsjo.weathersearch.model.ModelLocationSearch
import kr.bsjo.weathersearch.model.ModelWeather
import kr.bsjo.weathersearch.util.networkThread

class WeatherVm {

    private val tag get() = this::class.java.canonicalName

    val adapter = BaseRecyclerViewAdapter<WeatherItemVm, ItemWeatherBinding>(R.layout.item_weather, BR.vm)

    val progressVisibility = ObservableBoolean(true)

    private val disposable = CompositeDisposable()

    fun init() {
        locationSearch()
            .getWeatherList()
            .reduce { t1: List<ModelWeather>, t2: List<ModelWeather> -> t1 + t2 }
            .networkThread()
            .subscribe({ result ->
                progressVisibility.set(false)
                putData(result)
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

    private fun Observable<ModelLocationSearch.Response>.getWeatherList(): Observable<List<ModelWeather>> {
        return this.flatMapSingle { location ->
            ApiService.weather().location(location.woeid.toString())
                .map { ModelWeather(weathers = it.consolidated_weather.take(2), title = location.title).toList() }
        }
    }

    private fun putData(result: List<ModelWeather>) {
        adapter.clear()
        adapter.add(WeatherItemVm.createHeader())
        result.forEach { adapter.add(WeatherItemVm(it)) }
    }


    fun clearDisposable() {
        disposable.clear()
    }
}