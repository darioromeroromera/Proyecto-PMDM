package com.example.proyectopmdm.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectopmdm.MainActivity
import com.example.proyectopmdm.databinding.FragmentAboutUsBinding
import com.example.proyectopmdm.databinding.FragmentSettingsBinding

class AboutUsFragment : Fragment() {
    private lateinit var binding : FragmentAboutUsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutUsBinding.inflate(inflater, container, false)
        return binding.root
    }
}