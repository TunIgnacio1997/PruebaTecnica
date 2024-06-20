package com.ignaciotun.prueba_tecnica.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ignaciotun.prueba_tecnica.R
import com.ignaciotun.prueba_tecnica.core.Constantes
import com.ignaciotun.prueba_tecnica.models.PeliculaModel

class AdapterPeliculas (
    val context : Context,
    var listaPeliculaModel: List<PeliculaModel>,
    var listener: AdapterPeliculasInterface
) : RecyclerView.Adapter<AdapterPeliculas.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val namePelicula = itemView.findViewById<TextView>(R.id.textViewMovieName)
        val ratingPelicula = itemView.findViewById<TextView>(R.id.textViewMovieRating)
        val imgPelicula = itemView.findViewById<ImageView>(R.id.imageViewMoviePoster)
        val btnVista = itemView.findViewById<LinearLayout>(R.id.btnVista)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return ViewHolder(vista)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = listaPeliculaModel[position]

        holder.namePelicula.text = pelicula.originalTitle
        holder.ratingPelicula.text = pelicula.voteAverage.toString()
        Glide
            .with(context)
            .load("${Constantes.BASE_URL_IMAGE}${pelicula.posterPath}")
            .centerCrop()
            .into(holder.imgPelicula)
        holder.btnVista.setOnClickListener {
            listener.goDetailsMovie(pelicula)
        }
    }

    override fun getItemCount(): Int {
        return listaPeliculaModel.size
    }

    interface AdapterPeliculasInterface {
        fun goDetailsMovie(peliculaModel:PeliculaModel)
    }


}