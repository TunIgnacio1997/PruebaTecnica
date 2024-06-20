package com.ignaciotun.prueba_tecnica.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ignaciotun.prueba_tecnica.core.Constantes
import com.ignaciotun.prueba_tecnica.models.DetallePeliculaModel
import com.ignaciotun.prueba_tecnica.models.PeliculaModel
import com.ignaciotun.prueba_tecnica.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeliculasViewModel : ViewModel() {
    private var _listaPeliculas = MutableLiveData<List<PeliculaModel>>()
    val listaPeliculas : LiveData<List<PeliculaModel>> = _listaPeliculas

    private var _pelicula = MutableLiveData<DetallePeliculaModel>()
    val pelicula : LiveData<DetallePeliculaModel> = _pelicula
    fun getPeliculasList(){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getMovies(Constantes.API_KEY)
            withContext(Dispatchers.Main) {
                _listaPeliculas.value = response.body()!!.resultados.sortedBy { it.voteAverage }
            }
        }
    }

    fun getDetallePelicula(movieId:Int){
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getPelicula(movieId, Constantes.API_KEY)
            withContext(Dispatchers.Main) {
                _pelicula.value = response.body()
            }
        }
    }
}