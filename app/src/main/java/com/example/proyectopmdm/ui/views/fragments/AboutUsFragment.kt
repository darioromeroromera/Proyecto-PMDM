package com.example.proyectopmdm.ui.views.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.proyectopmdm.databinding.FragmentAboutUsBinding

class AboutUsFragment : Fragment() {
    private lateinit var binding : FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnGotoLocation.setOnClickListener {
            //Toast.makeText(requireContext(), "Botón pulsado", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:37.77667577322974, -3.788492410830741?z=18")
            }

            startActivity(intent)
        }
    }
}