package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.databinding.FragmentDetallesFragmentBinding
import com.example.tragosapp.ui.viewmodel.mainViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class detalles_fragment : Fragment() {

    private lateinit var trago: Trago
    private val viewModel by activityViewModels<mainViewModel>()
    private var _binding:FragmentDetallesFragmentBinding ?= null
    private val binding get() = _binding!!

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
        _binding = FragmentDetallesFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get().load(trago.imagen).resize(400,250).into(binding.ivTragoDetalle)
        binding.tvTragoTitulo.text = trago.nombre
        binding.tvTragoDescrip.text = trago.descripcion

        binding.btnFavoritos.setOnClickListener {
            viewModel.saveTragoFav(TragosEntity(trago.tragoId,trago.imagen,trago.nombre,trago.descripcion))
            Toast.makeText(requireContext(),"Se guardo tu Trago como Favorito",Toast.LENGTH_LONG).show()
        }

    }
}