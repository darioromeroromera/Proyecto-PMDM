package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectopmdm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var myuser: String
    private lateinit var mypass: String
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        myuser = getString(R.string.user)
        mypass = getString(R.string.password)
        binding.btnLogin.setOnClickListener { view ->
            if (binding.etUsuario.text.toString().trim().equals(myuser) && binding.etPassword.text.toString().equals(mypass)) {
                val intent = Intent(this, MainActivity::class.java).putExtra(
                    "user", binding.etUsuario.text.toString().trim()
                )

                startActivity(intent)
            } else
                Toast.makeText(this, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
        }
        binding.btnRegister.setOnClickListener { view ->
            Toast.makeText(this, "Funcionalidad no implementada",Toast.LENGTH_LONG).show()
        }
    }
}