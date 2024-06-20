package com.ignaciotun.prueba_tecnica.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.ignaciotun.prueba_tecnica.databinding.ActivityMainBinding
import com.ignaciotun.prueba_tecnica.models.PeliculaModel
import com.ignaciotun.prueba_tecnica.presentation.adapters.AdapterPeliculas
import com.ignaciotun.prueba_tecnica.presentation.ui.fragments.DetallePelicula
import com.ignaciotun.prueba_tecnica.presentation.viewmodels.PeliculasViewModel

class MainActivity : AppCompatActivity(), AdapterPeliculas.AdapterPeliculasInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : PeliculasViewModel
    private lateinit var adapterPeliculas: AdapterPeliculas
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        auth = Firebase.auth
        viewModel = ViewModelProvider(this)[PeliculasViewModel::class.java]
        recyclerViewInit()
        viewModel.getPeliculasList()

        viewModel.listaPeliculas.observe(this) {
            if(binding.swipeRefresh.isRefreshing){
                binding.swipeRefresh.isRefreshing = false
            }
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.visibility = View.GONE
            adapterPeliculas.listaPeliculaModel = it
            adapterPeliculas.notifyDataSetChanged()
        }

        binding.btnLogOut.setOnClickListener {
            logOut()
        }
        binding.txtBienvenida.text = "¡Bienvenido!"

        binding.swipeRefresh.setOnRefreshListener {
            binding.shimmerViewContainer.startShimmer()
            binding.shimmerViewContainer.visibility = View.VISIBLE
            viewModel.getPeliculasList()
        }
    }

    fun recyclerViewInit(){
        val layoutManager = GridLayoutManager(this, 2)
        binding.rvPeliculas.layoutManager = layoutManager
        adapterPeliculas = AdapterPeliculas(this, arrayListOf(), this)
        binding.rvPeliculas.adapter = adapterPeliculas
    }

    override fun goDetailsMovie(peliculaModel: PeliculaModel) {
        val bottomSheetFragment = DetallePelicula(peliculaModel) // Reemplaza con tu clase BottomSheet
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
    }

    fun logOut(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cerrar sesión")
            .setMessage("¿Esta seguro de cerrar sesión?.")
            .setPositiveButton("Aceptar") { _, _ ->
                FirebaseAuth.getInstance().signOut()
                goLogin()
            }
            .setNegativeButton("Cancelar") { _, _ ->
                // Acción al hacer clic en el botón "Cancelar"
            }
            .show()
    }

    fun goLogin(){
        val intent = Intent(
            this,
            Login::class.java
        )
        finish()
        startActivity(intent)
    }
}
