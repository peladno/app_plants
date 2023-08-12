package com.example.plantapp.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.plantapp.Adapter.PlantAdapter
import com.example.plantapp.R
import com.example.plantapp.Utils.PLANTID
import com.example.plantapp.ViewModel.PlantViewModel
import com.example.plantapp.databinding.CardItemBinding
import com.example.plantapp.databinding.FragmentItemListBinding

class ItemList : Fragment() {

    private lateinit var binding: FragmentItemListBinding
    private val plantViewModel: PlantViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemListBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlantAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)


        plantViewModel.getPlantList()
            .observe(viewLifecycleOwner, Observer {
                it.let {
                    adapter.update(it)
                }
            })

        adapter.selectedProduct().observe(viewLifecycleOwner) { plant ->
            plant?.let {
                val bundle = Bundle().apply { putInt(PLANTID, plant.id) }
                findNavController().navigate(R.id.action_item_list_to_item_detail, bundle)
            }
        }
    }
}


