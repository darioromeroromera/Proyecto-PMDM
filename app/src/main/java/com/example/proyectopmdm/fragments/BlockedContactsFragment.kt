package com.example.proyectopmdm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.proyectopmdm.R
import com.example.proyectopmdm.databinding.FragmentBlockedContactsBinding

class BlockedContactsFragment : Fragment() {
    private lateinit var binding : FragmentBlockedContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBlockedContactsBinding.inflate(inflater, container, false)
        return binding.root
    }
}