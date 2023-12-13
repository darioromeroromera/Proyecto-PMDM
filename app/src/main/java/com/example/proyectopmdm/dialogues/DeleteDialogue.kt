package com.example.proyectopmdm.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
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
                    getDialog()?.dismiss()
                })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}