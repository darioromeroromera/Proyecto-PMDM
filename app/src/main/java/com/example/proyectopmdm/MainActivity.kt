package com.example.proyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectopmdm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
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