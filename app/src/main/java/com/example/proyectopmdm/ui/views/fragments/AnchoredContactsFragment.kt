package com.example.proyectopmdm.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.proyectopmdm.databinding.FragmentAnchoredContactsBinding

class AnchoredContactsFragment : Fragment() {
    private lateinit var binding : FragmentAnchoredContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnchoredContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

}