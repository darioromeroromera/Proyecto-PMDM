package com.example.proyectopmdm.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.DialogLayoutBinding
import com.example.proyectopmdm.models.Contacto
import java.lang.IllegalStateException

class CreateDialogue(
    var okOnCreateContact: (Contacto) -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewContacto = inflater.inflate(R.layout.dialog_layout, null)
            builder.setView(viewContacto)
            builder.setMessage("Añadir Contacto")
            builder.setPositiveButton("Añadir",
                DialogInterface.OnClickListener { dialog, id ->
                    val contact = createContact(viewContacto)

                    if (isContactFilled(contact))
                        okOnCreateContact(contact)
                    else
                        Toast.makeText(activity, "Todos los campos excepto imagen son obligatorios", Toast.LENGTH_LONG).show()
                })
            builder.setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(activity, "Operación cancelada", Toast.LENGTH_LONG).show()
                    dialog?.dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createContact(view: View) : Contacto {
        val binding = DialogLayoutBinding.bind(view)
        return Contacto(
            binding.etNombre.text.toString(),
            binding.etNombreCompleto.text.toString(),
            binding.etTelefono.text.toString(),
            binding.etImagen.text.toString()
        )
    }

    private fun isContactFilled(contact: Contacto) : Boolean {
        return (! (contact.nombre.isNullOrBlank() || contact.nombreCompleto.isNullOrBlank() || contact.telefono.isNullOrBlank()))
    }
}