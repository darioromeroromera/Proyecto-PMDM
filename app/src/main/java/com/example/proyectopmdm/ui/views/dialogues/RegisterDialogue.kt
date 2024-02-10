package com.example.proyectopmdm.ui.views.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.data.models.Contacto
import com.example.proyectopmdm.data.models.User
import com.example.proyectopmdm.databinding.RegisterDialogLayoutBinding
import java.lang.IllegalStateException

class RegisterDialogue(
    var okOnCreateUser: (User) -> Unit
) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewUser = inflater.inflate(R.layout.register_dialog_layout, null)
            builder.setView(viewUser)
            builder.setMessage("Añadir Usuario")
            builder.setPositiveButton("Añadir",
                DialogInterface.OnClickListener { dialog, id ->
                    val user = createUser(viewUser)

                    if (isUserFilled(user))
                        if (isUserEmailCorrect(user))
                            okOnCreateUser(user)
                        else
                            Toast.makeText(activity, "Formato de email incorrecto", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(activity, "Todos los campos son obligatorios", Toast.LENGTH_LONG).show()
                })
            builder.setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    Toast.makeText(activity, "Operación cancelada", Toast.LENGTH_LONG).show()
                    dialog?.dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun createUser(view: View) : User {
        val binding = RegisterDialogLayoutBinding.bind(view)
        return User(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString(),
            binding.etEmail.text.toString()
        )
    }

    private fun isUserFilled(user: User) : Boolean {
        return (! (user.name.isNullOrBlank() || user.password.isNullOrBlank() || user.email.isNullOrBlank()))
    }

    private fun isUserEmailCorrect(user: User) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()
    }
}