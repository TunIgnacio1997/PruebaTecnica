package com.ignaciotun.prueba_tecnica.models

import com.google.gson.annotations.SerializedName

data class GenresModel(
    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null

)
