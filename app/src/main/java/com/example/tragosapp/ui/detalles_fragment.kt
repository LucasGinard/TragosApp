package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.tragosapp.R
import com.example.tragosapp.appDataBase
import com.example.tragosapp.data.dataSource
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.RepoImplement
import com.example.tragosapp.ui.viewmodel.mainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detalles_fragment.*

@AndroidEntryPoint
class detalles_fragment : Fragment() {

    private lateinit var trago: Trago
    private val viewModel by activityViewModels<mainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            trago = it.getParcelable<Trago>("trago")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalles_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(trago.imagen).resize(400,250).into(ivTragoDetalle)
        tvTragoTitulo.text = trago.nombre
        tvTragoDescrip.text = trago.descripcion

        btnFavoritos.setOnClickListener {
            viewModel.saveTragoFav(TragosEntity(trago.tragoId,trago.imagen,trago.nombre,trago.descripcion))
            Toast.makeText(requireContext(),"Se guardo tu Trago como Favorito",Toast.LENGTH_LONG).show()
        }

    }
}