package com.example.proyectopmdm.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopmdm.databinding.CardviewLayoutBinding
import com.example.proyectopmdm.models.Contacto

class ViewHContacto(view: View,
                    var deleteOnClick: (Int) -> Unit) : RecyclerView.ViewHolder(view) {
    var binding: CardviewLayoutBinding

    init {
        binding = CardviewLayoutBinding.bind(view)
    }

    fun renderize(contacto: Contacto) {
        binding.tvNombreContacto.text = contacto.nombre
        binding.tvNombreCompleto.text = contacto.nombreCompleto
        binding.tvTelefono.text = contacto.telefono
        Glide.with(itemView.context)
            .load(contacto.imagen)
            .centerCrop()
            .into(binding.ivContacto)
        setOnClickListener(adapterPosition)
    }

    private fun setOnClickListener(position: Int) {
        binding.btnDelete.setOnClickListener { view ->
            deleteOnClick(position)
        }
    }
}