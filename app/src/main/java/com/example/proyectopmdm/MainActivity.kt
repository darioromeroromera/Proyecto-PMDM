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
        binding.tvUsuario.setText(binding.tvUsuario.text.toString() + intent.getStringExtra("user"))
        binding.tvPassword.setText(binding.tvPassword.text.toString() + intent.getStringExtra("pass"))
    }
}