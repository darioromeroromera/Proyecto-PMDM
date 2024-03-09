package com.example.proyectopmdm.ui.views.dialogues

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.DialogLayoutBinding
import com.example.proyectopmdm.data.models.mem.Contacto
import com.example.proyectopmdm.databinding.RegisterDialogLayoutBinding
import com.example.proyectopmdm.domain.usecases.models.ContactModel
import com.example.proyectopmdm.ui.views.LoginActivity
import com.example.proyectopmdm.ui.views.MainActivity
import java.io.ByteArrayOutputStream
import java.lang.IllegalStateException

// Programa de Darío

class CreateDialogue(
    var okOnCreateContact: (ContactModel) -> Unit
) : DialogFragment() {
    private lateinit var mainActivity: MainActivity
    private lateinit var startCameraActivity: ActivityResultLauncher<Intent>
    private lateinit var startGalleyActivity: ActivityResultLauncher<Intent>
    private var bitmap : Bitmap? = null
    private lateinit var binding: DialogLayoutBinding
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted)
            takePicture()
        else
            Toast.makeText(requireActivity(), "Error: permisos denegados", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { activity ->
            val builder = AlertDialog.Builder(activity)
            val inflater = activity.layoutInflater
            val viewContacto = inflater.inflate(R.layout.dialog_layout, null)
            binding = DialogLayoutBinding.bind(viewContacto)
            setExternalActivities()
            binding.ivTakePicture.setOnClickListener {
                requestCameraPermission()
            }
            binding.ivPickFromGallery.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.setType("image/*")
                startGalleyActivity.launch(intent)
            }
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

    private fun createContact(view: View) : ContactModel {
        var urlImg : String? = null

        bitmap?.let {
            urlImg = " data:image/jpeg;base64," + convert(it)!!
        }

        return ContactModel(
            binding.etNombre.text.toString(),
            binding.etNombreCompleto.text.toString(),
            binding.etTelefono.text.toString(),
            binding.etDetalles.text.toString(),
            urlImg
        )
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

    private fun isContactFilled(contact: ContactModel) : Boolean {
        return (! (contact.nombre.isNullOrBlank() || contact.nombreCompleto.isNullOrBlank() || contact.telefono.isNullOrBlank()))
    }

    private fun setExternalActivities() {
        startCameraActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { result ->
            // Está obsoleto pero la manera correcta de hacerlo es para una API muy moderna y no va en mi teléfono
            //bitmap = result.data?.extras?.getParcelable("data", Bitmap::class.java) Esta es la manera moderna "correcta"
            bitmap = result.data?.extras?.get("data") as Bitmap

            binding.ivContactPicture.setImageBitmap(bitmap)
        })

        startGalleyActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult(), { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val imagenUri = result.data!!.data
                binding.ivContactPicture.setImageURI(imagenUri)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                    bitmap = MediaStore.Images.Media.getBitmap(mainActivity.contentResolver, imagenUri)
                } else {
                    bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(mainActivity.contentResolver, imagenUri!!))
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