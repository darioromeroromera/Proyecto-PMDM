package com.example.proyectopmdm.controller

import android.content.Context
import com.example.proyectopmdm.MainActivity
import com.example.proyectopmdm.adapter.ContactoAdapter
import com.example.proyectopmdm.dao.ContactosDao
import com.example.proyectopmdm.models.Contacto

class Controller(val context: Context){
    lateinit var listaContactos: MutableList<Contacto>
    lateinit var adapter : ContactoAdapter

    init {
        listaContactos = ContactosDao.dao.getContactsData().toMutableList()
        setAdapter()
    }

    fun setAdapter() {
        val myActivity = context as MainActivity
        adapter = ContactoAdapter(listaContactos, {
                pos -> delHotel(pos)
        })
        myActivity.binding.rvContactos.adapter = adapter
    }

    private fun delHotel(pos: Int) {
        listaContactos.removeAt(pos)
        adapter.notifyItemRemoved(pos)
        adapter.notifyItemChanged(pos)
        adapter.notifyDataSetChanged()
    }
}