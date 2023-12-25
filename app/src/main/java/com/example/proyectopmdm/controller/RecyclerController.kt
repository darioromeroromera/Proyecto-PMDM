package com.example.proyectopmdm.controller

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.MainActivity
import com.example.proyectopmdm.adapter.ContactoAdapter
import com.example.proyectopmdm.dao.ContactosDao
import com.example.proyectopmdm.databinding.FragmentRecyclerBinding
import com.example.proyectopmdm.dialogues.CreateDialogue
import com.example.proyectopmdm.dialogues.DeleteDialogue
import com.example.proyectopmdm.dialogues.EditDialogue
import com.example.proyectopmdm.models.Contacto

class RecyclerController(
    val context: FragmentActivity,
    val fragmentBinding: FragmentRecyclerBinding){
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
        initEvent()

    }

    private fun initLayoutManager() {
        layoutManager = fragmentBinding.rvContactos.layoutManager as LinearLayoutManager
    }

    fun setAdapter() {
        adapter = ContactoAdapter(listaContactos, {
                pos -> delContact(pos)
        },
            {
                pos, contacto -> editContacto(pos, contacto)
            })
        fragmentBinding.rvContactos.adapter = adapter
    }

    private fun initEvent() {
        fragmentBinding.fbAdd.setOnClickListener { view ->
            addContacto()
        }
    }

    private fun editContacto(pos: Int, contacto: Contacto) {
        val dialog = EditDialogue(
            pos,
            contacto,
            {
                contact, pos -> okOnEditContact(contact, pos)
            }
        )
        dialog.show(mainActivity.supportFragmentManager, "Editar")
    }

    private fun okOnEditContact(contact: Contacto, pos: Int) {
        val oldContacto = listaContactos.get(pos)
        oldContacto.nombre = contact.nombre
        oldContacto.nombreCompleto = contact.nombreCompleto
        oldContacto.telefono = contact.telefono
        oldContacto.imagen = contact.imagen
        adapter.notifyItemChanged(pos)
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