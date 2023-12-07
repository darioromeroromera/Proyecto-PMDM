package com.example.proyectopmdm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyectopmdm.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    val MYUSER = R.string.user
    val MYPASS = R.string.password
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvent()
    }

    private fun initEvent() {
        binding.btnLogin.setOnClickListener { view ->
            if (binding.etUsuario.text.toString().equals(MYUSER) && binding.etPassword.text.toString().equals(MYPASS)) {
                val intent = Intent(this, MainActivity::class.java).putExtra(
                    "user", binding.etUsuario.text.toString()
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