package com.example.proyectopmdm.ui.views.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.RegisterDialogLayoutBinding
import com.example.proyectopmdm.domain.usecases.models.UserModel
import java.lang.IllegalStateException
import android.Manifest;
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class RegisterDialogue(
    var okOnCreateUser: (UserModel) -> Unit
) : DialogFragment() {
    private lateinit var binding: RegisterDialogLayoutBinding
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted)
            takePicture()
        else
            Toast.makeText(requireActivity(), "Error: permisos denegados", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewUser = inflater.inflate(R.layout.register_dialog_layout, null)
            binding = RegisterDialogLayoutBinding.bind(viewUser)
            binding.ivProfilePicture.setOnClickListener {
                Toast.makeText(requireActivity(), "Botón cámara pulsado", Toast.LENGTH_SHORT).show()
            }
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

    private fun createUser(view: View) : UserModel {
        return UserModel(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString(),
            binding.etEmail.text.toString(),
            ""
        )
    }

    private fun isUserFilled(user: UserModel) : Boolean {
        return (! (user.name.isNullOrBlank() || user.password.isNullOrBlank() || user.email.isNullOrBlank()))
    }

    private fun isUserEmailCorrect(user: UserModel) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (hasPermission())
                takePicture()
            else
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else
            takePicture()
    }

    private fun hasPermission() : Boolean = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

}