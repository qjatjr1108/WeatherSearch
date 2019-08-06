package kr.bsjo.weathersearch.scene

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import kr.bsjo.weathersearch.BR
import kr.bsjo.weathersearch.R
import kr.bsjo.weathersearch.databinding.ActivityWeatherBinding

class WeatherActivity : AppCompatActivity() {

    private val viewModel = createVm()

    private fun createVm() = WeatherVm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
            .setVariable(BR.vm, viewModel)

        viewModel.init()
    }
}
