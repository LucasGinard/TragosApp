package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tragosapp.R
import com.example.tragosapp.appDataBase
import com.example.tragosapp.data.dataSource
import com.example.tragosapp.data.model.Trago
import com.example.tragosapp.data.model.TragosEntity
import com.example.tragosapp.domain.RepoImplement
import com.example.tragosapp.domain.tragoDao
import com.example.tragosapp.ui.viewmodel.mainViewModel
import com.example.tragosapp.vo.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favoritos.*
import kotlinx.android.synthetic.main.fragment_main.*
import okhttp3.internal.notify
import javax.inject.Inject

@AndroidEntryPoint
class favoritosFragment : Fragment(),favAdapter.onTragoClickListener2 {


    private val viewModel by activityViewModels<mainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewTragos()
        setupObserver()

    }

    private fun setupObserver(){
        viewModel.getTragosFav().observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is Resource.Loading ->{

                }
                is Resource.Sucess ->{
                    var adapter = favAdapter(requireContext(),result.data,this)
                    rvFavoritos.adapter = adapter
                    val swipeHandler = object : swipedDeleteTrago(requireContext()) {
                        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                            viewModel.deleteTragos(adapter.getTragoPo(viewHolder.adapterPosition))
                            setupObserver()
                            adapter.notifyItemRemoved(viewHolder.adapterPosition)
                        }
                    }
                    val itemTouchHelper = ItemTouchHelper(swipeHandler)
                    itemTouchHelper.attachToRecyclerView(rvFavoritos)
                }
                is Resource.Failure ->{

                }
            }
        })
    }

    private fun recyclerviewTragos(){
        rvFavoritos.layoutManager = LinearLayoutManager(requireContext())
        rvFavoritos.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onTragoClick(trago: TragosEntity) {
        val bundle = Bundle()
        val Trago:Trago = Trago(trago.tragoid,trago.imagen,trago.nombre,trago.descripcion)
        bundle.putParcelable("trago",Trago)
        findNavController().navigate(R.id.action_favoritosFragment_to_detalles_fragment,bundle)
    }
}