package com.example.proyectopmdm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.proyectopmdm.MainActivity
import com.example.proyectopmdm.databinding.FragmentDetailBinding
import com.example.proyectopmdm.models.Contacto
import com.google.android.material.internal.ToolbarUtils

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
        Glide.with(this).load(arg.imagen).centerCrop().into(binding.ivDetContacto)
        binding.ivDetContacto.clipToOutline = true
        binding.tvDetNombre.text = arg.nombre
        binding.tvDetNombreCompleto.text = arg.nombreCompleto
        binding.tvDetTelefono.text = arg.telefono
        binding.tvDetDetalles.text = arg.detalles
    }
}