package com.example.tragosapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tragosapp.R
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.databinding.FragmentMainBinding
import com.example.tragosapp.ui.viewmodel.mainViewModel
import com.example.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment(),mainAdapter.onTragoClickListenerFav {

    private val viewModel by activityViewModels<mainViewModel>()
    private var _binding:FragmentMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewTragos()
        setupBuscador()
        setupObserver()
        binding.btnFavoritosMain.setOnClickListener {
            findNavController().navigate(R.id.favoritosFragment)
        }
    }
        


    private fun setupObserver() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.pbCargar.visibility = View.VISIBLE
                }
                is Resource.Sucess -> {
                    binding.pbCargar.visibility = View.GONE
                    binding.rvTragos.adapter = mainAdapter(requireContext(), result.data, this)
                }
                is Resource.Failure -> {
                    binding.pbCargar.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error Compruebe su Conexión ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun recyclerviewTragos(){
        binding.rvTragos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTragos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setupBuscador(){
        binding.svBuscador.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(pedi: String?): Boolean {
                if (pedi != null) {
                    viewModel.setTrago(pedi)
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onTragoClick(trago: Trago) {
        val bundle = Bundle()
        bundle.putParcelable("trago", trago)
        findNavController().navigate(R.id.action_mainFragment_to_detalles_fragment, bundle)
    }
}