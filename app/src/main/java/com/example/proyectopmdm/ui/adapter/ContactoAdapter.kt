package com.example.proyectopmdm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.R
import com.example.proyectopmdm.data.models.mem.MutableRepository
import com.example.proyectopmdm.data.models.network.MutableContactRepository

class ContactoAdapter: RecyclerView.Adapter<ViewHContacto>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHContacto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardViewLayout = R.layout.cardview_layout
        return ViewHContacto(layoutInflater.inflate(cardViewLayout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHContacto, position: Int) {
        holder.renderize(MutableContactRepository.contacts.get(position))
    }

    override fun getItemCount(): Int {
        return MutableContactRepository.contacts.size
    }
}