package com.example.proyectopmdm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.proyectopmdm.data.models.mem.MutableRepository
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.databinding.FragmentDetailBinding

class DetailFragment() : Fragment() {
    private lateinit var binding : FragmentDetailBinding
    val arg : DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val contact = MutableContactRepository.contacts.get(arg.pos)
        Glide.with(this).load(contact.imagen).centerCrop().into(binding.ivDetContacto)
        binding.ivDetContacto.clipToOutline = true
        binding.tvDetNombre.text = contact.nombre
        binding.tvDetNombreCompleto.text = contact.nombreCompleto
        binding.tvDetTelefono.text = contact.telefono
        binding.tvDetDetalles.text = contact.detalles
    }
}