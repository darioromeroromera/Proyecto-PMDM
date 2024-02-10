package com.example.proyectopmdm.ui.views.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.DialogLayoutBinding
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.datasource.Fields

class EditDialogue(
    val pos: Int,
    val contacto: Contacto,
    val okOnEditContact: (Contacto, Int) -> Unit
) : DialogFragment() {

    init {
        val args = Bundle().apply{
            putString(Fields.NAME_FIELD, contacto.nombre)
            putString(Fields.FULL_NAME_FIELD, contacto.nombreCompleto)
            putString(Fields.PHONE_FIELD, contacto.telefono)
            putString(Fields.URL_FIELD, contacto.imagen)
            putString(Fields.DETAIL_FIELD, contacto.detalles)
        }
        this.arguments = args
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{ activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewContacto = inflater.inflate(R.layout.dialog_layout, null)
            builder.setView(viewContacto)
            setFields(viewContacto)
            builder.setMessage("Editar contacto")
            builder.setPositiveButton("Editar",
                DialogInterface.OnClickListener { dialog, id ->
                    val contact = createContact(viewContacto)

                    if (isContactFilled(contact))
                        okOnEditContact(contact, pos)
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

    private fun setFields(view: View) {
        val binding = DialogLayoutBinding.bind(view)
        arguments?.let{ args->
            binding.etNombre.setText(args.getString(Fields.NAME_FIELD))
            binding.etNombreCompleto.setText(args.getString(Fields.FULL_NAME_FIELD))
            binding.etTelefono.setText(args.getString(Fields.PHONE_FIELD))
            binding.etImagen.setText(args.getString(Fields.URL_FIELD))
            binding.etDetalles.setText(args.getString(Fields.DETAIL_FIELD))
        }
    }

    private fun createContact(view: View) : Contacto {
        val binding = DialogLayoutBinding.bind(view)
        return Contacto(
            binding.etNombre.text.toString(),
            binding.etNombreCompleto.text.toString(),
            binding.etTelefono.text.toString(),
            binding.etImagen.text.toString(),
            binding.etDetalles.text.toString()
        )
    }

    private fun isContactFilled(contact: Contacto) : Boolean {
        return (! (contact.nombre.isNullOrBlank() || contact.nombreCompleto.isNullOrBlank() || contact.telefono.isNullOrBlank()))
    }
}