package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.proyectopmdm.controller.RecyclerController
import com.example.proyectopmdm.databinding.ActivityMainBinding
import com.example.proyectopmdm.fragments.RecyclerFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var recyclerController: RecyclerController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        val user = intent.getStringExtra("user")
        binding.tvSaludo.text = binding.tvSaludo.text.toString() + user
    }
}