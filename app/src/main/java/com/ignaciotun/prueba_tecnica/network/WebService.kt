package com.ignaciotun.prueba_tecnica.network

import com.ignaciotun.prueba_tecnica.models.DetallePeliculaModel
import com.ignaciotun.prueba_tecnica.network.Response.PeliculaResponse
import com.ignaciotun.prueba_tecnica.presentation.ui.fragments.DetallePelicula
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {
    @GET("now_playing")
    suspend fun getMovies(
        @Query("api_key")  apiKey:String
    ) : Response<PeliculaResponse>

    @GET("{movie_id}")
    suspend fun getPelicula(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ) : Response<DetallePeliculaModel>
}