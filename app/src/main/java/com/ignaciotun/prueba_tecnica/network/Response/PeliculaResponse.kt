package com.ignaciotun.prueba_tecnica.network.Response

import com.google.gson.annotations.SerializedName
import com.ignaciotun.prueba_tecnica.models.PeliculaModel

data class PeliculaResponse(
    @SerializedName("results")
    var resultados : List<PeliculaModel>
)
