package com.ignaciotun.prueba_tecnica.presentation.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.ignaciotun.prueba_tecnica.core.Constantes
import com.ignaciotun.prueba_tecnica.databinding.FragmentDetallePeliculaBinding
import com.ignaciotun.prueba_tecnica.models.DetallePeliculaModel
import com.ignaciotun.prueba_tecnica.models.PeliculaModel
import com.ignaciotun.prueba_tecnica.presentation.viewmodels.PeliculasViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DetallePelicula(val pelicula: PeliculaModel) : BottomSheetDialogFragment() {

    lateinit var binding: FragmentDetallePeliculaBinding
    private lateinit var viewModel : PeliculasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetallePeliculaBinding.inflate(inflater,container,false)
        return binding.getRoot()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this)[PeliculasViewModel::class.java]

        viewModel.getDetallePelicula(pelicula.id)
        viewModel.pelicula.observe(this) {
            initValues(it)
        }

        Glide
            .with(requireActivity())
            .load("${Constantes.BASE_URL_IMAGE}${pelicula.backdropPath}")
            .centerCrop()
            .into(binding.imageViewMoviePoster)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun initValues(peli: DetallePeliculaModel){
        binding.textViewMovieTitle.text = peli.title
        binding.textViewDuration.text = "Duracion: ${peli.runtime} min"
        binding.textViewDescription.text = peli.overview
        binding.textViewReleaseDate.text = "Fecha de estreno:${ peli.releaseDate }"
        val genreNames = peli.genres.joinToString(", ") { it.name.toString() }
        binding.textViewGenres.text = "Géneros: $genreNames"
        if (peli.adult!!){
            binding.textViewRating.text = "Clasificacion: Adultos"
        } else {
            binding.textViewRating.text = "Clasificacion: Familiar"
        }

    }
}