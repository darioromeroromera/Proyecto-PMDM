package com.example.proyectopmdm.controller

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.ui.views.MainActivity
import com.example.proyectopmdm.ui.adapter.ContactoAdapter
import com.example.proyectopmdm.databinding.FragmentRecyclerBinding
import com.example.proyectopmdm.ui.views.dialogues.CreateDialogue
import com.example.proyectopmdm.ui.views.dialogues.DeleteDialogue
import com.example.proyectopmdm.ui.views.dialogues.EditDialogue
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.ui.views.fragments.RecyclerFragmentDirections

class RecyclerController(
    val context: FragmentActivity){
    /*lateinit var adapter : ContactoAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var fragmentBinding: FragmentRecyclerBinding

     fun initLayoutManager() {
        layoutManager = fragmentBinding.rvContactos.layoutManager as LinearLayoutManager
    }

    fun setAdapter() {
        adapter = ContactoAdapter({
                pos -> delContact(pos)
        },
            {
                pos, contacto -> editContacto(pos, contacto)
            },
            { contacto ->
                val navController = fragmentBinding.rvContactos.findNavController()
                navController.navigate(RecyclerFragmentDirections.actionRecyclerFragmentToDetailsFragment(
                    nombre=contacto.nombre, nombreCompleto = contacto.nombreCompleto, telefono = contacto.telefono,
                    imagen = contacto.imagen, detalles = contacto.detalles
                ))
            })
        fragmentBinding.rvContactos.adapter = adapter
    }

    fun initEvent() {
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
        oldContacto.detalles = contact.detalles
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
    }*/

}