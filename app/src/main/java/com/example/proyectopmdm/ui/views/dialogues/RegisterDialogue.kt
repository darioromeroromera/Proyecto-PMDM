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
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.graphics.decodeBitmap
import androidx.room.util.query
import com.example.proyectopmdm.ui.views.LoginActivity
import com.example.proyectopmdm.ui.views.MainActivity
import java.io.ByteArrayOutputStream
import kotlin.math.log

class RegisterDialogue(
    var okOnCreateUser: (UserModel) -> Unit
) : DialogFragment() {
    private lateinit var loginActivity: LoginActivity
    private lateinit var startCameraActivity: ActivityResultLauncher<Intent>
    private lateinit var startGalleyActivity: ActivityResultLauncher<Intent>
    private var bitmap : Bitmap? = null
    private lateinit var binding: RegisterDialogLayoutBinding
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted)
            takePicture()
        else
            Toast.makeText(requireActivity(), "Error: permisos denegados", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewUser = inflater.inflate(R.layout.register_dialog_layout, null)
            binding = RegisterDialogLayoutBinding.bind(viewUser)
            setExternalActivities()
            binding.ivTakePicture.setOnClickListener {
                requestCameraPermission()
            }
            binding.ivPickFromGallery.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startGalleyActivity.launch(intent)
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

        var urlImg: String? = null

        bitmap?.let {
            urlImg = " data:image/jpeg;base64," + convert(it)!!
        }

        return UserModel(
            binding.etUsername.text.toString(),
            binding.etPassword.text.toString(),
            binding.etEmail.text.toString(),
            urlImg
        )
    }

    private fun isUserFilled(user: UserModel) : Boolean {
        return (! (user.name.isNullOrBlank() || user.password.isNullOrBlank() || user.email.isNullOrBlank()))
    }

    private fun isUserEmailCorrect(user: UserModel) : Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()
    }

    private fun requestCameraPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (hasCameraPermission())
                takePicture()
            else
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        } else
            takePicture()
    }

    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startCameraActivity.launch(intent)
    }

    private fun hasCameraPermission() : Boolean = ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED


    private fun setExternalActivities() {
        startCameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { result ->
            // Está obsoleto pero la manera correcta de hacerlo es para una API muy moderna y no va en mi teléfono
            //bitmap = result.data?.extras?.getParcelable("data", Bitmap::class.java) Esta es la manera moderna "correcta"
            bitmap = result.data?.extras?.get("data") as Bitmap

            binding.ivProfilePicture.setImageBitmap(bitmap)
        })

        startGalleyActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imagenUri = result.data!!.data
                binding.ivProfilePicture.setImageURI(imagenUri)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    bitmap = MediaStore.Images.Media.getBitmap(loginActivity.contentResolver, imagenUri)
                } else {
                    bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(loginActivity.contentResolver, imagenUri!!))
                }
            }
        })
    }

    private fun convert(bitmap: Bitmap): String? {
        val byteArray = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
        val data = byteArray.toByteArray()
        return Base64.encodeToString(data, Base64.DEFAULT)
    }
}