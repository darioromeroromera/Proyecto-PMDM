package com.example.proyectopmdm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.R
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.MutableRepository
import javax.inject.Inject

class ContactoAdapter: RecyclerView.Adapter<ViewHContacto>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHContacto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardViewLayout = R.layout.cardview_layout
        return ViewHContacto(layoutInflater.inflate(cardViewLayout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHContacto, position: Int) {
        holder.renderize(MutableRepository.contacts.get(position))
    }

    override fun getItemCount(): Int {
        return MutableRepository.contacts.size
    }
}