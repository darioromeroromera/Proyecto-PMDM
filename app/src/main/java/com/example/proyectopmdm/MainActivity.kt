package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectopmdm.controller.Controller
import com.example.proyectopmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        initController()
        initEvent()
    }

    private fun initRecycler() {
        binding.rvContactos.layoutManager = LinearLayoutManager(this)
    }

    private fun initController() {
        controller = Controller(this)
    }

    private fun initEvent() {
        val user = intent.getStringExtra("user")
        binding.tvSaludo.text = binding.tvSaludo.text.toString() + user
    }
}