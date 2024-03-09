package com.example.proyectopmdm.ui.adapter

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectopmdm.databinding.CardviewLayoutBinding
import com.example.proyectopmdm.data.models.mem.MutableRepository
import com.example.proyectopmdm.data.models.network.MutableContactRepository
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.ui.views.MainActivity
import com.example.proyectopmdm.ui.views.dialogues.DeleteDialogue
import com.example.proyectopmdm.ui.views.dialogues.EditDialogue
import com.example.proyectopmdm.ui.views.fragments.RecyclerFragmentDirections

class ViewHContacto(view: View) : RecyclerView.ViewHolder(view) {
    var binding: CardviewLayoutBinding
    private var mainActivity : MainActivity

    init {
        binding = CardviewLayoutBinding.bind(view)
        mainActivity = view.context as MainActivity
    }

    fun renderize(contacto: ContactModel) {
        binding.tvNombreContacto.text = contacto.nombre
        binding.tvNombreCompleto.text = contacto.nombreCompleto
        binding.tvTelefono.text = contacto.telefono
        Glide.with(itemView.context).load(contacto.imagen).centerCrop().into(binding.ivContacto)
        setOnClickListener(adapterPosition, contacto)
    }

    private fun setOnClickListener(position: Int, contacto: ContactModel) {
        binding.btnDelete.setOnClickListener { view ->
            delContact(position)

        }

        binding.btnEdit.setOnClickListener { view ->
            editContacto(position, contacto)
        }

        binding.btnInfo.setOnClickListener { view ->
            mainActivity.navController.navigate(RecyclerFragmentDirections.actionRecyclerFragmentToDetailsFragment(position))
        }
    }

    private fun delContact(pos: Int) {

        val dialog = DeleteDialogue(
            pos,
            MutableContactRepository.contacts.get(pos).nombreCompleto,
            {
                    pos ->
                        val token = mainActivity.shared.getString("token", "defValue")!!
                        val id = MutableContactRepository.contacts.get(pos).id!!
                        mainActivity.contactsViewModel.removeContact(token, id, pos)

                        //Toast.makeText(mainActivity, MutableContactRepository.contacts.get(pos).nombreCompleto, Toast.LENGTH_SHORT).show()
            })
        dialog.show(mainActivity.supportFragmentManager, "Borrar")
    }

    private fun editContacto(pos: Int, contacto: ContactModel) {
        val dialog = EditDialogue(
            pos,
            contacto
        ) { contact, pos -> //mainActivity.contactsViewModel.updateContact(pos, contact)
        }
        dialog.show(mainActivity.supportFragmentManager, "Editar")
    }
}