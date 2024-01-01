package com.example.proyectopmdm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectopmdm.R
import com.example.proyectopmdm.models.Contacto

class ContactoAdapter(val listaContactos: MutableList<Contacto>,
                       var deleteOnClick: (Int) -> Unit,
                        var editOnClick: (Int, Contacto) -> Unit,
                        var infoOnClick: (Contacto) -> Unit): RecyclerView.Adapter<ViewHContacto>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHContacto {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cardViewLayout = R.layout.cardview_layout
        return ViewHContacto(layoutInflater.inflate(cardViewLayout, parent, false), deleteOnClick, editOnClick, infoOnClick)
    }

    override fun onBindViewHolder(holder: ViewHContacto, position: Int) {
        holder.renderize(listaContactos.get(position))
    }

    override fun getItemCount(): Int {
        return listaContactos.size
    }

}