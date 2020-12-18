package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tragosapp.R
import com.example.tragosapp.appDataBase
import com.example.tragosapp.data.dataSource
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.domain.RepoImplement
import com.example.tragosapp.ui.viewmodel.mainViewModel
import com.example.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(),mainAdapter.onTragoClickListenerFav {

    private val viewModel by activityViewModels<mainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewTragos()
        setupBuscador()
        setupObserver()
        btnFavoritosMain.setOnClickListener {
            findNavController().navigate(R.id.favoritosFragment)
        }
    }

    private fun setupObserver() {
        viewModel.fetchTragosList.observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is Resource.Loading -> {
                    pbCargar.visibility = View.VISIBLE
                }
                is Resource.Sucess -> {
                    pbCargar.visibility = View.GONE
                    rvTragos.adapter = mainAdapter(requireContext(),result.data,this)
                }
                is Resource.Failure -> {
                    pbCargar.visibility = View.GONE
                    Toast.makeText(requireContext(),"Error Compruebe su Conexión ${result.exception}",Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun recyclerviewTragos(){
        rvTragos.layoutManager = LinearLayoutManager(requireContext())
        rvTragos.addItemDecoration(DividerItemDecoration(requireContext(),DividerItemDecoration.VERTICAL))
    }

    private fun setupBuscador(){
        svBuscador.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(pedi: String?): Boolean {
                viewModel.setTrago(pedi!!)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
    }

    override fun onTragoClick(trago: Trago) {
        val bundle = Bundle()
        bundle.putParcelable("trago",trago)
        findNavController().navigate(R.id.action_mainFragment_to_detalles_fragment,bundle)
    }
}