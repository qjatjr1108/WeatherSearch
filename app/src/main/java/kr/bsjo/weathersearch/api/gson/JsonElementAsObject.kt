package kr.bsjo.weathersearch.api.gson

import kr.bsjo.weathersearch.model.type.OutputType

class JsonElementAsObject(obj: Any) : JsonElementAs(obj, OutputType.ToObject)
