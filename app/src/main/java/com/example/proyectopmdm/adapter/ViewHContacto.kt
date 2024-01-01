package com.example.proyectopmdm.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopmdm.databinding.CardviewLayoutBinding
import com.example.proyectopmdm.models.Contacto

class ViewHContacto(view: View,
                    var deleteOnClick: (Int) -> Unit,
                    var editOnClick: (Int, Contacto) -> Unit,
                    var infoOnClick: (Contacto) -> Unit) : RecyclerView.ViewHolder(view) {
    var binding: CardviewLayoutBinding

    init {
        binding = CardviewLayoutBinding.bind(view)
    }

    fun renderize(contacto: Contacto) {
        binding.tvNombreContacto.text = contacto.nombre
        binding.tvNombreCompleto.text = contacto.nombreCompleto
        binding.tvTelefono.text = contacto.telefono
        Glide.with(itemView.context).load(contacto.imagen).centerCrop().into(binding.ivContacto)
        setOnClickListener(adapterPosition, contacto)
    }

    private fun setOnClickListener(position: Int, contacto: Contacto) {
        binding.btnDelete.setOnClickListener { view ->
            deleteOnClick(position)
        }

        binding.btnEdit.setOnClickListener { view ->
            editOnClick(position, contacto)
        }

        binding.btnInfo.setOnClickListener { view ->
            infoOnClick(contacto)
        }
    }
}