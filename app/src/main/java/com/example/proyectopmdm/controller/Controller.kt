package com.example.proyectopmdm.controller

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.MainActivity
import com.example.proyectopmdm.adapter.ContactoAdapter
import com.example.proyectopmdm.dao.ContactosDao
import com.example.proyectopmdm.dialogues.CreateDialogue
import com.example.proyectopmdm.dialogues.DeleteDialogue
import com.example.proyectopmdm.models.Contacto

class Controller(val context: Context){
    lateinit var listaContactos: MutableList<Contacto>
    lateinit var adapter : ContactoAdapter
    lateinit var mainActivity: MainActivity
    lateinit var layoutManager: LinearLayoutManager

    init {
        listaContactos = ContactosDao.dao.getContactsData().toMutableList()
        mainActivity = context as MainActivity
        initLayoutManager()
        setAdapter()
        initEvent()
    }

    private fun initLayoutManager() {
        layoutManager = mainActivity.binding.rvContactos.layoutManager as LinearLayoutManager
    }

    fun setAdapter() {
        adapter = ContactoAdapter(listaContactos, {
                pos -> delContact(pos)
        })
        mainActivity.binding.rvContactos.adapter = adapter
    }

    private fun initEvent() {
        mainActivity.binding.fbAdd.setOnClickListener { view ->
            addContacto()
        }
    }

    private fun addContacto() {
        val dialog = CreateDialogue(

            {
            contact -> okOnCreateContact(contact)
        })
        dialog.show(mainActivity.supportFragmentManager, "Añadir")
    }

    private fun okOnCreateContact(contact: Contacto) {
        listaContactos.add(contact)
        adapter.notifyItemInserted(listaContactos.lastIndex)
        layoutManager.scrollToPositionWithOffset(listaContactos.lastIndex, 20)
    }

    private fun delContact(pos: Int) {

        val dialog = DeleteDialogue(
            pos,
            listaContactos.get(pos).nombreCompleto,
            {
            pos -> okOnDeleteContact(pos)
        })
        dialog.show(mainActivity.supportFragmentManager, "Borrar")

    }

    private fun okOnDeleteContact(pos: Int) {
        listaContactos.removeAt(pos)
        adapter.notifyItemRemoved(pos)
        adapter.notifyItemChanged(pos)
        adapter.notifyDataSetChanged()
    }
}