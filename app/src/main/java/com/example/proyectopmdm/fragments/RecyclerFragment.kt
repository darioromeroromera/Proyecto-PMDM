package com.example.proyectopmdm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.contentValuesOf
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.controller.RecyclerController
import com.example.proyectopmdm.databinding.FragmentRecyclerBinding

class RecyclerFragment : Fragment() {
    lateinit var binding : FragmentRecyclerBinding
    lateinit var navController : NavController
    lateinit var recyclerController : RecyclerController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvContactos.layoutManager = LinearLayoutManager(requireActivity())
        navController = NavHostFragment.findNavController(this)
        initController()
    }

    private fun initController() {
        recyclerController = RecyclerController(requireActivity(), binding)
    }
}