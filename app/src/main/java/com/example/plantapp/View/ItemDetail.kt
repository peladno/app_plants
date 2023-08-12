package com.example.plantapp.View

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.plantapp.R
import com.example.plantapp.Utils.PLANTID
import com.example.plantapp.ViewModel.PlantViewModel
import com.example.plantapp.databinding.FragmentItemDetailBinding

class ItemDetail : Fragment() {

    private lateinit var binding: FragmentItemDetailBinding
    private val plantViewModel: PlantViewModel by activityViewModels()
    private var plantId: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailBinding.inflate(
            inflater, container, false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle -> plantId = bundle.getInt(PLANTID) }

        plantId?.let { id -> plantViewModel.fetchPlantDetail(id) }

        plantViewModel.getPlantDetail().observe(viewLifecycleOwner) { plant ->
            Glide.get(requireContext()).clearMemory()
            Glide.with(binding.itemImg)
                .load(plant?.image)
                .into(binding.itemImg)
            binding.itemName.text = plant?.name
            binding.itemDescription.text = plant?.description
            binding.itemType.text = plant?.type
            binding.buttonDetail.setOnClickListener {
                sendEmail(
                    plant?.name ?: ""
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        plantViewModel.clearData()
    }

    private fun sendEmail(name: String) {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("j.perezurrutia@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Solicitud información sobre producto $name")
            putExtra(
                Intent.EXTRA_TEXT,
                "Hola,\nQuisiera pedir información sobre este producto " +
                        "$name.\nMe gustaría que me contactaran a este correo."
            )
        }

        try {
            startActivity(emailIntent)
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
    }

}