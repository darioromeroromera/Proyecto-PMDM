package com.example.proyectopmdm.ui.views.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.lang.IllegalStateException

class DeleteDialogue(
    val pos: Int,
    val nombre: String,
    var onDeleteClick: (Int) -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage("¿Quieres eliminar a $nombre?")
            builder.setPositiveButton("Sí",
                DialogInterface.OnClickListener { dialog, id ->
                    onDeleteClick(pos)
                })
            builder.setNegativeButton("No",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(activity, "Operación cancelada", Toast.LENGTH_LONG).show()
                    getDialog()?.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}