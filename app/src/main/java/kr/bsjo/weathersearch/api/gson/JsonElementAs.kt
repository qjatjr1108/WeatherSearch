package kr.bsjo.weathersearch.api.gson

import kr.bsjo.weathersearch.model.type.OutputType
import java.io.Serializable

open class JsonElementAs(val obj: Any, val outputType: OutputType) : Serializable {
    fun <T> get(): T = obj as T

    override fun toString() = GsonHelper.toJson(obj)
}
